package com.beautysalon.activity;


import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.customer.CustomerService;
import com.beautysalon.employee.Employee;
import com.beautysalon.employee.EmployeeRepository;
import com.beautysalon.service.Service;
import com.beautysalon.service.ServiceRepository;
import com.beautysalon.customer.Customer;
import com.beautysalon.customer.CustomerRepository;
import jakarta.inject.Singleton;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ActivityServiceTest {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ActivityMapper mapper;

    @BeforeEach
    void setup(){
        activityService = new ActivityService(activityRepository, employeeRepository ,serviceRepository ,customerRepository , customerService, mapper);
    }

    @AfterEach
    void clean(){
        customerRepository.deleteAll();
        activityRepository.deleteAll();
        activityRepository.deleteAll();
    }
    @Test
    void testSaveActivityMethod() {
        //Arrange
        String mockName = "test user";
        Customer customer = new Customer();
        customer.setUserKeycloakId(mockName);
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getName()).thenReturn(mockName);
        customer.setCreatedBy(authentication.getName());
        Long customerId = customerRepository.save(customer).getId();

        Employee employee = new Employee();
        employee.setCreatedBy(authentication.getName());
        Long employeeId = employeeRepository.save(employee).getId();
        Service service = new Service();
        service.setCreatedBy(authentication.getName());
        service.setDuration(LocalTime.of(1, 30, 0));
        Long serviceId = serviceRepository.save(service).getId();
        ActivityRequest activityRequest =
                new ActivityRequest(
                        LocalDate.of(2023, 10, 10),
                        LocalTime.of(12, 0, 0),
                        "dfasdf",
                        false,
                        10.0,
                        true,
                        employeeId,
                        serviceId);

        Activity expectedActivity = new Activity();
        expectedActivity.setId(1L);
        expectedActivity.setCreatedBy(authentication.getName());
        expectedActivity.setDate(LocalDate.of(2023, 10, 10));
        expectedActivity.setStartTime(LocalTime.of(12, 0, 0));
        expectedActivity.setFinishTime(LocalTime.of(13, 30, 0));
        expectedActivity.setRemarks("dfasdf");
        expectedActivity.setTaskDone(false);
        expectedActivity.setDeposit(10);
        expectedActivity.setDepositPaid(true);
        expectedActivity.setCustomer(customer);
        expectedActivity.setEmployee(employee);
        expectedActivity.setService(service);


        //Act
        Long result = activityService.saveActivityWithConnectedUser(activityRequest, authentication);

        //Assert
        assertEquals(expectedActivity.getId(), result);

    }

    @Test
    void testSaveActivityMethodWithDuplicateActivities() {

        //Arrange
        String mockName = "test user";
        Customer customer = new Customer();
        customer.setUserKeycloakId(mockName);
        when(customerRepository.findCustomerByUserKeycloakId(customer.getUserKeycloakId())).thenReturn(customer);
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getName()).thenReturn(mockName);

        Employee employee = new Employee();
        employee.setId(1L);

        Service service = new Service();
        service.setId(50L);
        service.setDuration(LocalTime.of(1, 30, 0));
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(serviceRepository.findById(service.getId())).thenReturn(Optional.of(service));
        ActivityRequest activityRequest = new ActivityRequest(
                        LocalDate.of(2023, 10, 10),
                        LocalTime.of(12, 0, 0),
                        "dfasdf",
                        false,
                        10.0,
                        true,
                        employee.getId(),
                        service.getId());

        Activity expectedActivity = new Activity();
        expectedActivity.setId(1L);
        expectedActivity.setDate(LocalDate.of(2023, 10, 9));
        expectedActivity.setStartTime(LocalTime.of(12, 0, 0));
        expectedActivity.setFinishTime(LocalTime.of(13, 30, 0));
        expectedActivity.setRemarks("dfasdf");
        expectedActivity.setTaskDone(false);
        expectedActivity.setDeposit(10);
        expectedActivity.setDepositPaid(true);
        expectedActivity.setCustomer(customer);
        expectedActivity.setEmployee(employee);
        expectedActivity.setService(service);

        when(mapper.map(activityRequest)).thenReturn(expectedActivity);
        when(activityRepository.save(expectedActivity)).thenReturn(expectedActivity);

        //Act
        Long result = activityService.saveActivityWithConnectedUser(activityRequest, authentication);
        Activity activity = activityRepository.findById(result).orElseThrow();
        System.out.println(result);
        System.out.println(activity);
        //Assert
        assertEquals(expectedActivity.getId(), result);
        verify(mapper).map(activityRequest);
        verify(activityRepository, times(1)).save(expectedActivity);
    }

}
