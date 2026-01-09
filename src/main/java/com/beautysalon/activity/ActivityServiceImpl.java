package com.beautysalon.activity;


import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.activity.dto.ActivityResponse;
import com.beautysalon.serviceentity.ServiceEntity;
import com.beautysalon.serviceentity.ServiceEntityRepository;
import com.beautysalon.user.UserEntity;
import com.beautysalon.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper mapper;
    private final ServiceEntityRepository serviceEntityRepository;
    private final UserRepository userRepository;


    @Override
    public void saveActivity(ActivityRequest activityRequest, Authentication authentication) {
        final ServiceEntity serviceEntity = serviceEntityRepository.findById(activityRequest.getServiceEntityId()).orElseThrow(() -> new EntityNotFoundException("Product not found with id :" + activityRequest.getServiceEntityId()));
        UserEntity employee = userRepository.findById(activityRequest.getEmployeeId()).orElseThrow(() -> new EntityNotFoundException("User not found with id :" + activityRequest.getEmployeeId()));
        UserEntity customer = userRepository.findByUsername(authentication.getName());
        Activity activity = mapper.map(activityRequest);
        activity.getUserEntityList().add(employee);
        activity.getUserEntityList().add(customer);
        activity.setServiceEntity(serviceEntity);
        Activity saved = activityRepository.save(activity);
        List<Activity> employeActivityList = employee.getActivities();
        employeActivityList.add(saved);
        employee.setActivities(employeActivityList);
        UserEntity savedEmployee = userRepository.save(employee);
        List<Activity> customerActivityList = customer.getActivities();
        customerActivityList.add(saved);
        customer.setActivities(customerActivityList);
        UserEntity savedCustomer = userRepository.save(customer);

    }


    public List<ActivityResponse> findActivitiesForEmployeeIdAndDate(String id, LocalDate date) {
        UserEntity employee = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No employee found"));
        List<Activity> activities = activityRepository.findActivitiesByEmployeeIdAndDate(employee.getUserId(), date);
        return activities.stream().map(mapper::map).toList();
    }


}
