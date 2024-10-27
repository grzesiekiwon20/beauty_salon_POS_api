package com.beautysalon.activity;


import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.activity.dto.ActivityResponse;
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

    private final ActivityRepository activityRepository;
    private final EmployeeRepository employeeRepository;
    private final ActivityMapper mapper;

    public Long saveActivityWithConnectedUser(
            ActivityRequest activityRequest,
            Authentication connectedUser,
            Long typeId,
            Long employeeId
            ) {
        Activity activity = mapper.map(activityRequest);
        activity.setUserId(connectedUser.getName());
        Employee employee =
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(()-> new NullPointerException("No employee found"));
        activity.setEmployeeId(employee.getId());
        activity.setTypeId(typeId);

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
        List<Activity> activities = activityRepository.findByUserId(connectedUser.getName());
        return activities
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public ActivityResponse findById(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(()->new NullPointerException("No Activity Found"));

        return mapper.map(activity);
    }
}
