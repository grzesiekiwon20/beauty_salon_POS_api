package com.beautysalon.activity;


import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.activity.dto.ActivityResponse;
import com.beautysalon.activity.dto.DaysResponse;
import com.beautysalon.employee.Employee;
import com.beautysalon.employee.EmployeeRepository;
import com.beautysalon.service.Service;
import com.beautysalon.service.ServiceRepository;
import com.beautysalon.customer.Customer;
import com.beautysalon.customer.CustomerRepository;
import com.beautysalon.customer.CustomerService;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final EmployeeRepository employeeRepository;
    private final ServiceRepository serviceRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final ActivityMapper mapper;

    public ActivityService(ActivityRepository activityRepository, EmployeeRepository employeeRepository, ServiceRepository serviceRepository, CustomerRepository customerRepository, CustomerService customerService, ActivityMapper mapper) {
        this.activityRepository = activityRepository;
        this.employeeRepository = employeeRepository;
        this.serviceRepository = serviceRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.mapper = mapper;
    }

    public Long saveActivityWithConnectedUser(
            ActivityRequest activityRequest,
            Authentication connectedUser
    ) {
        if (customerRepository.findCustomerByUserKeycloakId(connectedUser.getName()) == null) {
            Long userId = customerService.saveUserIntoRepository(connectedUser.getName());
        }
        Customer customer = customerRepository.findCustomerByUserKeycloakId(connectedUser.getName());
        Employee employee = employeeRepository.findById(activityRequest.employeeId()).orElseThrow(() -> new NullPointerException("No employee found"));
        Service service = serviceRepository.findById(activityRequest.serviceId()).orElseThrow(() -> new NullPointerException("No type found"));
        Activity activity = mapper.map(activityRequest);
        activity.setCustomer(customer);
        activity.setService(service);
        activity.setEmployee(employee);
        activity.setFinishTime(activityRequest.startTime().plusHours(service.getDuration().getHour()).plusMinutes(service.getDuration().getMinute()));
        List<Activity> listOfEmployeeActivities = activityRepository
                .findActivitiesByEmployeeIdAndDate(employee.getId(), activity.getDate())
                .stream()
                .toList();

        if (checkAvailability(listOfEmployeeActivities, activity)) {
            return activityRepository.save(activity).getId();
        } else {
            throw new RuntimeException("Activity time not available, please choose different time or date");
        }

    }

    public List<ActivityResponse> findAllActivities() {
        return activityRepository
                .findAll()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<ActivityResponse> findActivitiesByUserId(Authentication connectedUser) {
        if (customerRepository.findCustomerByUserKeycloakId(connectedUser.getName()) == null) {
            Long id = customerService.saveUserIntoRepository(connectedUser.getName());
            return activityRepository.findActivityByCustomerId(id).stream().map(mapper::map).toList();

        } else {
            Customer customer = customerRepository.findCustomerByUserKeycloakId(connectedUser.getName());
            List<Activity> activities = activityRepository.findActivityByCustomerId(customer.getId());
            return activities
                    .stream()
                    .map(mapper::map)
                    .collect(Collectors.toList());
        }
    }

    public ActivityResponse findById(Long activityId) {
        Activity activity = activityRepository
                .findById(activityId)
                .orElseThrow(
                        () -> new NullPointerException("No Activity Found"));
        return mapper.map(activity);
    }

    public List<LocalTime> findAvailableTimesOfDay(List<Activity> existingActivities) {
        LocalTime openTime = LocalTime.of(9, 0, 0);
        LocalTime closeTime = LocalTime.of(18, 0, 0);
        List<LocalTime> timeWindowArray = splitTimes(LocalTime.of(0, 0), LocalTime.of(1, 30));
        List<LocalTime> timeTable = new ArrayList<>();
        while (openTime.isBefore(closeTime)) {
            timeTable.add(openTime);
            openTime = openTime.plusHours(1).plusMinutes(30);
        }
        existingActivities.forEach(activity -> {
            List<LocalTime> subList = splitTimes(activity.getStartTime(), activity.getFinishTime());
            for (LocalTime localTime : subList) {
                timeTable.remove(localTime);
            }
        });
        return timeTable.stream().sorted().toList();
    }


    public boolean checkAvailability(List<Activity> existingActivitiesList, Activity activity) {
        LocalTime openTime = LocalTime.of(9, 0, 0);
        LocalTime closeTime = LocalTime.of(16, 30, 0);
        HashMap<LocalTime, Boolean> timeTable = this.timeTable(openTime, closeTime);

        existingActivitiesList.forEach(existingActivity -> {
            List<LocalTime> times = this.splitTimes(existingActivity.getStartTime(), existingActivity.getFinishTime());
            times.forEach(localTime -> {
                timeTable.put(localTime, true);
            });
        });

        List<LocalTime> times = splitTimes(activity.getStartTime(), activity.getFinishTime());

        for (LocalTime localTime : times) {
            if (timeTable.containsKey(localTime) && timeTable.get(localTime)) {
                return false;
            }
        }
        return true;
    }

    public HashMap<LocalTime, Boolean> timeTable(LocalTime start, LocalTime finish) {
        HashMap<LocalTime, Boolean> timeMap = new HashMap<>();
        while (start.isBefore(finish)) {
            timeMap.put(start, false);
            start = start.plusMinutes(15);
        }
        return timeMap;
    }

    public List<LocalTime> splitTimes(LocalTime start, LocalTime finish) {
        List<LocalTime> result = new ArrayList<>();
        while (start.isBefore(finish)) {
            result.add(start);
            start = start.plusMinutes(15);
        }
        return result;
    }

    public List<LocalTime> findAvailableTimesForEmployee(Long id, LocalDate date) {

        Employee employee = employeeRepository
                    .findById(id)
                    .orElseThrow(() -> new NullPointerException("No employee found"));
        List<Activity> activities = new ArrayList<>(activityRepository.findActivitiesByEmployeeIdAndDate(employee.getId(), date));


        return findAvailableTimesOfDay(activities);
    }

    public List<ActivityResponse> findActivityListForEmployee(Long employeeId, LocalDate date) {
        return activityRepository.findActivitiesByEmployeeIdAndDate(employeeId, date).stream().map(mapper::map).toList();
    }

    public DaysResponse findNumberOfDays(Authentication authentication) {
        String message = "You don't have booked appointments at this moment.";
        LocalDate today = LocalDate.now();
        Customer customer = customerRepository.findCustomerByUserKeycloakId(authentication.getName());
        if (customer == null) {
            return new DaysResponse(false, "Customer not found", null);
        }
        List<Activity> activities = activityRepository.findActivityByCustomerId(customer.getId());
        if (activities == null || activities.isEmpty()) {
            return new DaysResponse(false, "You don't have booked appointments at this moment.", null);
        }
        activities.sort(Comparator.comparing(Activity::getDate));
        LocalDate nextActivityDate = activities.get(0).getDate();

        if (today.isBefore(nextActivityDate)) {
            long days = ChronoUnit.DAYS.between(today, nextActivityDate);
            return new DaysResponse(true, "Next booking is in " + days + " days.", days);
        } else {
            return new DaysResponse(false, "Date of booking already passed.", null);
        }
    }
}
