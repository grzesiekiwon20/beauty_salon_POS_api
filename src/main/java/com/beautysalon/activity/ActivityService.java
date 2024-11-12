package com.beautysalon.activity;


import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.activity.dto.ActivityResponse;
import com.beautysalon.customer.Customer;
import com.beautysalon.customer.CustomerRepository;
import com.beautysalon.employee.Employee;
import com.beautysalon.employee.EmployeeRepository;
import com.beautysalon.type.Type;
import com.beautysalon.type.TypeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final EmployeeRepository employeeRepository;
    private final TypeRepository typeRepository;
    private final CustomerRepository customerRepository;
    private final ActivityMapper mapper;

    public ActivityService(ActivityRepository activityRepository, EmployeeRepository employeeRepository, TypeRepository typeRepository, CustomerRepository customerRepository, ActivityMapper mapper) {
        this.activityRepository = activityRepository;
        this.employeeRepository = employeeRepository;
        this.typeRepository = typeRepository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    public Long saveActivityWithConnectedUser(
            ActivityRequest activityRequest,
            Authentication connectedUser,
            Long typeId,
            Long employeeId
            ) {
        if(customerRepository.findByUserId(connectedUser.getName()) == null){
            Customer customer = new Customer();
            customer.setUserId(connectedUser.getName());
            customer.setActivities(new ArrayList<>());
            customerRepository.save(customer);
        }
        Customer customer = customerRepository.findByUserId(connectedUser.getName());
        Activity activity = mapper.map(activityRequest);
        activity.setCustomer(customer);
        Employee employee =
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(()-> new NullPointerException("No employee found"));
        Type type = typeRepository
                .findById(typeId)
                .orElseThrow(()-> new NullPointerException("No type found"));
        activity.setEmployee(employee);
        activity.setType(type);

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
        if(customerRepository.findByUserId(connectedUser.getName()) == null){
            throw new NullPointerException("No customer found");
        }
        else {
            Customer customer = customerRepository.findByUserId(connectedUser.getName());
            List<Activity> activities = activityRepository.findActivityByCustomerId(customer.getId());
            return activities
                    .stream()
                    .map(mapper::map)
                    .collect(Collectors.toList());
        }
    }

    public ActivityResponse findById(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(()->new NullPointerException("No Activity Found"));

        return mapper.map(activity);
    }
}
