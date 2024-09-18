package com.beautysalon.activity;


import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.activity.dto.ActivityResponse;
import com.beautysalon.type.Type;
import com.beautysalon.type.TypeRepository;
import com.beautysalon.user.User;
import com.beautysalon.user.UserRepository;
import com.beautysalon.employee.Employee;
import com.beautysalon.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ActivityService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final EmployeeRepository employeeRepository;
    private final TypeRepository typeRepository;
    private final ActivityMapper mapper;

    public Long saveActivityWithConnectedUser(
            ActivityRequest activityRequest,
            Authentication connectedUser,
            Long typeId,
            Long employeeId
            ) {
        User client = (User) connectedUser.getPrincipal(); // gets logged in user
        Activity activity = mapper.map(activityRequest);// maps activity request into activity
        User savedUserClient =
                userRepository
                        .findById(client.getId())
                        .orElseThrow(() -> new NullPointerException("No user found"));//gets logged-in user details
        activity.setUserId(savedUserClient.getId()); // sets logged in client into activity
        Employee employee =
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(()-> new NullPointerException("No employee found"));
        activity.setEmployeeId(employee.getId());
        activity.setTypeId(typeId);

        return activityRepository.save(activity).getId();
    }


    public Long saveActivityWithConnectedEmployee(
            ActivityRequest activityRequest,
            Authentication connectedUser,
            String clientName
    ) {

        User client = userRepository.findByFullName(clientName).orElseThrow(() -> new NullPointerException("No client saved"));
        Activity activity = mapper.map(activityRequest);

//        activity.setClient(client);

        return activityRepository.save(activity).getId();
    }

    public List<ActivityResponse> findAllActivities() {
        return activityRepository
                .findAll()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<ActivityResponse> findActivitiesByUserId(Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        User savedUser = userRepository.findById(user.getId()).orElseThrow(()-> new NullPointerException("No User Found"));
        return savedUser.getActivities()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public ActivityResponse findActivityById(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(()->new NullPointerException("No Activity Found"));

        return mapper.map(activity);
    }
}
