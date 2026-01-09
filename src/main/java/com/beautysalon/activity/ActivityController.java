package com.beautysalon.activity;

import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.serviceentity.ServiceEntityServiceImpl;
import com.beautysalon.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@RequestMapping("activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityServiceImpl activityService;
    private final UserServiceImpl userServiceImpl;
    private final ServiceEntityServiceImpl serviceEntityService;


    @GetMapping("/addNew")
    public String createActivity(Model model, @RequestParam(required = false) Long serviceId) {
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setServiceEntityId(serviceId);
        final String roleName = "EMPLOYEE";
        activityRequest.setServiceEntityId(serviceId);
        model.addAttribute("activity", activityRequest);
        model.addAttribute("employeeList" , userServiceImpl.getUsersByRole(roleName));
        model.addAttribute("servicesList", serviceEntityService.findAllServices());
        model.addAttribute("serviceDetails" ,serviceEntityService.findById(serviceId));
        return "/activity/activitymng";
    }

    @PostMapping("/save")
    public String saveActivity(
            @ModelAttribute("activity") ActivityRequest activityRequest, Authentication authentication
    ) {
        activityService.saveActivity(activityRequest, authentication);

        return "redirect:/";
    }


    @GetMapping("/dates")
    public String getAvailableTimesForEmployee(
            Model model,
            @RequestParam LocalDate date,
            @RequestParam String employeeId
    ) {
        model.addAttribute("dates", activityService.findActivitiesForEmployeeIdAndDate(employeeId, date));

        return "/activity/activitymng";
    }

}
