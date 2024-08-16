package com.beautysalon.service.impl;

import com.beautysalon.controller.dto.*;
import com.beautysalon.repository.ActivityRepository;
import com.beautysalon.repository.AddressRepository;
import com.beautysalon.repository.UserRepository;
import com.beautysalon.repository.model.Activity;
import com.beautysalon.repository.model.ServiceType;
import com.beautysalon.repository.model.Address;
import com.beautysalon.repository.model.User;
import com.beautysalon.repository.model.UserType;
import com.beautysalon.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GeneralServiceImpl implements GeneralService {

    private final CustomMapperImpl mapper;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    //UserService methods implementation**
    @Override
    public void save(final UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new RuntimeException("User with given email already exists");
        }
        if(!isValidEmail(userRequest.getEmail())){
            throw new RuntimeException("Incorrect email format.");
        }
        final User user = mapper.map(userRequest);
        userRepository.save(user);
    }

    @Override
    public UserResponse findUserResponseByEmail(final String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new NullPointerException("User with this email does not exists!");
        }
        final User user = userRepository.findByEmail(email);
        return mapper.map(user);
    }

    @Override
    public UserResponse findUserResponseById(final Long id) {
        final User user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.map(user);
    }

    public List<UserResponse> findListOfUserResponsesByUserType(UserType userType) {
        List<User> userList = userRepository.findByUserType(userType);
        return userList.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(final Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserResponse> findListOfUserResponses() {
        final List<User> usersList = userRepository.findAll();
        return usersList.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findUserResponseByName(String name) {
        if (!userRepository.existsByName(name)) {
            throw new NullPointerException("Client with this name does not exist!");
        }
        final User user = userRepository.findByName(name);
        return mapper.map(user);
    }


    //ActivityService methods implementation
    @Override
    public void save(final ActivityRequest activityRequest) {
        if (userRepository.existsByEmail(activityRequest.getUser().getEmail())) {
            final User user = userRepository.findByEmail(activityRequest.getUser().getEmail());
            final Activity activity = mapper.map(activityRequest);
            activity.setUser(user);
            activityRepository.save(activity);
        } else {
            throw new NullPointerException("User Not Found!");
        }


    }

    public boolean getBookingAvailability(ActivityRequest activityRequest) {
        return true;
    }

    @Override
    public List<ActivityResponse> findAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityResponse findActivityById(final Integer activityId) {
        return activityRepository
                .findById(activityId)
                .map(mapper::map
                )
                .orElseThrow(() -> new IllegalArgumentException("Activity with id: " + activityId + " not found"));
    }

    @Override
    public List<ActivityResponse> findActivityResponsesByServiceTypeAndUserType(final ServiceType serviceType, final UserType userType) {
        final List<Activity> activities = activityRepository.findByServiceType(serviceType);
        return activities.stream()
                .filter(activity -> activity.getUserType().equals(userType))
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityResponse> findListOfActivityResponsesByDateAndUserType(final LocalDate date, final UserType userType) {
        final List<Activity> activities = activityRepository.findByDate(date);
        return activities.stream()
                .filter(activity -> activity.getUserType().equals(userType))
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityResponse> findListOfActivityResponsesByEmail(final String email) {
        final List<Activity> activities = activityRepository.findByUserEmail(email);
        return activities.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

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

    //AddressService methods Implementation
    @Override
    public void save(final AddressRequest request) {

        if (userRepository.existsByEmail(request.getUser().getEmail())) {
            final User user = userRepository.findByEmail(request.getUser().getEmail());
            final Address address = mapper.map(request);
            address.setUser(user);
            addressRepository.save(address);
        } else {
            throw new NullPointerException("User Not Found!");
        }
    }



    private boolean isValidEmail(String email) {
        final String EMAIL_REGEX = "^(?=.{1,64}@.{1,255}$)(?=.{1,64}@)(?=.*[^.].*)(?=.*\\.).+[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        return EMAIL_PATTERN.matcher(email).matches() && email.length() <= 255;
    }

}
