package com.beautysalon.service.impl;

import com.beautysalon.controller.dto.*;
import com.beautysalon.repository.*;
import com.beautysalon.repository.model.*;
import com.beautysalon.repository.model.users.User;
import com.beautysalon.service.GeneralService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GeneralServiceImpl implements GeneralService {


    private final CustomMapperImpl mapper;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;


    //UserService methods implementation
    @Override
    @Transactional
    public void saveUser(final UserRequest userRequest) {
        if(isValidEmail(userRequest.email())) {
            final User user = mapper.map(userRequest);
            final String password = user.getPassword();
            final String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setEnabled(true);
            userRepository.save(user);
        }else{
            throw new RuntimeException("User with email "+ userRequest.email() + " already exist");
        }

    }

//    @Override
//    public UserResponse userResponseByUsername(String username) {
//        final User user = userRepository.findByLoginDetails_Username(username);
//        return mapper.map(user);
//    }

    @Override
    @Transactional
    public UserResponse findUserResponseByEmail(final String userEmail) {
        return null;
    }

    @Override
    public UserResponse findUserResponseById(Long userId) {
        return userRepository
                .findById(userId)
                .map(mapper::map)
                .orElseThrow(() -> new NullPointerException("No User found"));
    }

    @Override
    public void deleteUserById(Long userId) {

    }

    @Transactional
    public List<UserResponse> findListOfUserResponses() {
        List<User> users = userRepository.findAll();
        return users.stream().map(mapper::map).collect(Collectors.toList());
    }


    @Override
    public List<UserResponse> findListOfUserResponsesByUserType(UserType userType) {
        return null;
    }


    //ActivityService methods implementation
    @Override
    @Transactional
    public void saveActivity(final ActivityRequest activityRequest) {

        if (getActivityAvailability(activityRequest)) {
            final Activity activity = mapper.map(activityRequest);
            activityRepository.save(activity);
        } else {
            throw new RuntimeException("No activity available");
        }

    }

    public List<ActivityResponse> findActivitiesByUserId(final Long userId) {
        final List<Activity> activityList = activityRepository.findAll();

        List<Activity> activityListWithUsers = new ArrayList<>();
        activityList.forEach(activity -> {
            activity.getUsers().forEach(user -> {
                        if (user.getId().equals(userId)) {
                            activityListWithUsers.add(activity);
                        }
                    }
            );
        });
        if (!activityListWithUsers.isEmpty()) {
            return activityListWithUsers.stream()
                    .map(mapper::map)
                    .collect(Collectors.toList());
        } else {
            throw new NullPointerException("No Activities for User found");
        }
    }

    @Transactional
    @Override
    public Optional<Activity> assignUsersToActivity(final Long activityId, final List<Long> userIds) {
        final Optional<Activity> activity = activityRepository.findById(activityId);
        HashSet<UserType> userTypes = new HashSet<>();
        for (Long id : userIds) {
            Optional<User> user = userRepository.findById(id);
            user.ifPresent(value -> {
                if (value.getUserType() == UserType.ADMINISTRATOR) {
                    throw new RuntimeException("User type Administrator can not be set for activity");
                }
            });
            user.ifPresent(value -> {
                userTypes.add(value.getUserType());
            });
        }
        if (activity.isEmpty()) {
            throw new RuntimeException("No activity found");
        }
        if (userTypes.size() != 2) {
            throw new RuntimeException("Incorrect set or numbers of users");
        } else {
            return activityRepository.findById(activityId)
                    .map(finalActivity -> {
                        finalActivity.setUsers(userRepository.findAllById(userIds));
                        return activityRepository.save(finalActivity);
                    });
        }
    }

    @Override
    public boolean getActivityAvailability(ActivityRequest activityRequest) {
        final Activity quotedActivity = mapper.map(activityRequest);

        final List<List<Activity>> employeeActivityList = userRepository
                .findAll()
                .stream()
                .filter(user -> user.getUserType().equals(UserType.EMPLOYEE))
                .map(User::getActivities)
                .toList();
        final List<Activity> listOfNotAssignedActivities = activityRepository.findAll();
        final List<Activity> inRepositoryACtivityList = new ArrayList<>();
        for (List<Activity> activities : employeeActivityList) {
            inRepositoryACtivityList.addAll(activities);
        }

        if (checkTimes(quotedActivity, listOfNotAssignedActivities)) return false;
        if (checkTimes(quotedActivity, inRepositoryACtivityList)) return false;
        return true;
    }

    private boolean checkTimes(Activity quotedActivity, List<Activity> listOfNotAssignedActivities) {
        for (Activity savedActivity : listOfNotAssignedActivities) {
            if (savedActivity.getDate().equals(quotedActivity.getDate())) {
                if (quotedActivity.getStartTime().equals(savedActivity.getStartTime()) && quotedActivity.getFinishTime().equals(savedActivity.getFinishTime())) {
                    return true;
                }
                if (quotedActivity.getStartTime().isAfter(savedActivity.getStartTime()) && quotedActivity.getStartTime().isBefore(savedActivity.getFinishTime())) {
                    return true;
                }
                if (quotedActivity.getStartTime().isBefore(savedActivity.getFinishTime()) && quotedActivity.getFinishTime().isAfter(savedActivity.getStartTime())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<ActivityResponse> findAllActivityResponses() {
        final List<Activity> activities = activityRepository.findAll();
        return activities.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }



//    @Override
//    public List<ActivityResponse> findListOfActivityResponsesByEmail(final String email) {
//        final List<Activity> activities = activityRepository.findByUserEmail(email);
//        return activities.stream()
//                .map(mapper::map)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<ActivityResponse> findListOfActivityResponsesByStartTime(final String startTime) {
        final LocalTime start = LocalTime.parse(startTime);
        final List<Activity> activities = activityRepository.findByStartTime(start);
        return activities.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityResponse> findListOfActivityResponsesByFinishTime(String finishTime) {
        final LocalTime finish = LocalTime.parse(finishTime);
        final List<Activity> activities = activityRepository.findByFinishTime(finish);
        return activities.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityResponse> findListOfActivityResponsesByEmail(final String email) {
        final Long userId = findUserIdByEmail(email);
        return findActivitiesByUserId(userId);
    }

    public List<ActivityResponse> findListOfActivityResponseByServiceType(final ServiceType serviceType) {
        List<Activity> activities = activityRepository.findAll();
        List<ActivityResponse> activityResponseList = new ArrayList<>();
        activities.forEach(activity -> {
            if (activity.getServiceType().equals(serviceType)) {
                activityResponseList.add(mapper.map(activity));
            }
        });
        return activityResponseList;
    }
    @Override
    public List<ActivityResponse> findListOfActivityResponsesByUsername(String username) {
        final Long id = findIdByUsername(username);
        final List<Activity> activities = activityRepository.findAll();
        final List<ActivityResponse> activityResponses = new ArrayList<>();
        activities.forEach(activity -> {
            activity.getUsers().forEach(user -> {
                if(Objects.equals(user.getId(), id)){
                    activityResponses.add(mapper.map(activity));
                }
            });
        });

        return activityResponses;
    }



    //AddressService methods Implementation
    @Override
    public void saveAddress(final AddressRequest request) {
        final Address address = mapper.map(request);
        addressRepository.save(address);
    }

    public Optional<Address> addressAssignment(final Long addressId, final List<Long> userIds) {
        return addressRepository.findById(addressId)
                .map(address -> {
                    address.setUsers(userRepository.findAllById(userIds));
                    return addressRepository.save(address);
                });
    }


    private Long findIdByUsername(String email) {
        final UserResponse userResponse = mapper.map(userRepository.findByEmail(email));
        if(userResponse != null){
            return userResponse.id();
        }else {
            throw new NullPointerException("No user found with username: " + email);
        }
    }


    private boolean isValidEmail(String email) {
        final String EMAIL_REGEX = "^(?=.{1,64}@.{1,255}$)(?=.{1,64}@)(?=.*[^.].*)(?=.*\\.).+[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        return EMAIL_PATTERN.matcher(email).matches() && email.length() <= 255;
    }

    private Long findUserIdByEmail(final String email) {
        final UserResponse userResponse = mapper.map(userRepository.findByEmail(email));
        if (userResponse != null) {
            return userResponse.id();
        } else {
            throw new NullPointerException("No User Found");
        }
    }
}
