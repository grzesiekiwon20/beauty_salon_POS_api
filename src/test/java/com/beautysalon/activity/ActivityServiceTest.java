package com.beautysalon.activity;


import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.employee.Employee;
import com.beautysalon.employee.EmployeeRepository;
import com.beautysalon.service.Service;
import com.beautysalon.service.ServiceRepository;
import com.beautysalon.customer.Customer;
import com.beautysalon.customer.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
public class ActivityServiceTest {

    @InjectMocks
    private ActivityService activityService;

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ActivityMapper mapper;



    @Test
    void testSaveActivityMethod() {
        //arrange
//        ActivityRequest activityRequest =
//                new ActivityRequest(LocalDate.of(2023, 10, 10), LocalTime.of(12, 0, 0), "dfasdf", false, 10.0, true);

        String mockName = "testuser";
        Customer customer = new Customer();
        customer.setUserKeycloakId(mockName);
//        when(customerRepository.findByCustomerKeycloakId(mockName)).thenReturn(customer);

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getName()).thenReturn(mockName);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        Employee employee = new Employee();
        employee.setId(1L);
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

        Service service = new Service();
        service.setId(50L);
        service.setDuration(LocalTime.of(1, 30,0));
        when(serviceRepository.findById(service.getId())).thenReturn(Optional.of(service));
//        Activity existingActivity1 = new Activity(); existingActivity1.setDate(LocalDate.of(2023, 10,10)); existingActivity1.setStartTime(LocalTime.of(10,0, 0));
//        Activity existingActivity2 = new Activity(); existingActivity1.setDate(LocalDate.of(2023, 10,10)); existingActivity1.setStartTime(LocalTime.of(14,0, 0));
        List<Activity> activities = new ArrayList<>();
//        activities.add(existingActivity1);
//        activities.add((existingActivity2));
        Activity activity = new Activity();activity.setDate(LocalDate.of(2023, 10, 10)); activity.setStartTime(LocalTime.of(12, 0, 0));

//        when(mapper.map(activityRequest)).thenReturn(activity);
        when(activityRepository.findActivitiesByEmployeeId(employee.getId())).thenReturn(activities);
        when(activityRepository.save(activity)).thenReturn(activity);

//        Long result = activityService.saveActivityWithConnectedUser(activityRequest, authentication, service.getId(), employee.getId());

//        verify(mapper).map(activity);
        verify(activityRepository, times(1)).save(activity);

//        Assertions.assertNotNull(result);
    }


}
