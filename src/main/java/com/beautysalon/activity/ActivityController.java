package com.beautysalon.activity;

import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.activity.dto.ActivityResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "Activity", description = "The Activity Api")
@RequiredArgsConstructor
@RequestMapping("activities")
public class ActivityController {

    private final ActivityService service;

    @PostMapping("/create/usr/{typeId}/{employeeId}")
    public ResponseEntity<Long> saveActivityByUser(
            @Valid @RequestBody ActivityRequest activityRequest,
            Authentication connectedUser,
            @PathVariable Long typeId,
            @PathVariable Long employeeId
            ){
        return ResponseEntity.ok(service.saveActivityWithConnectedUser(activityRequest, connectedUser, typeId,employeeId));
    }

    @PostMapping("/create/emp/{typeId}/{userId}")
    public ResponseEntity<Long> saveActivityByEmployee(
            @Valid @RequestBody ActivityRequest activityRequest,
            Authentication connectedUser,
            @PathVariable Long typeId,
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(service.saveActivityWithConnectedEmployee(activityRequest,connectedUser,typeId,userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActivityResponse>> getAllActivities(){
        return ResponseEntity.ok(service.findAllActivities());
    }

    @GetMapping("/connectedUser")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByConnectedUserId(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findActivitiesByUserId(connectedUser));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById(
            @PathVariable Long activityId
    ){
        return ResponseEntity.ok(service.findById(activityId));
    }

}
