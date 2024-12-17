package com.beautysalon.activity;

import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.activity.dto.ActivityResponse;
import com.beautysalon.activity.dto.DaysResponse;
import com.beautysalon.exception.BookingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@RestController
@Tag(name = "Activity", description = "The Activity Api")
@RequestMapping("activities")
public class ActivityController {

    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }


    @PostMapping("/create/usr")
    public ResponseEntity<Long> saveActivityByUser(
            @Valid @RequestBody ActivityRequest activityRequest,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.saveActivityWithConnectedUser(activityRequest, connectedUser));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActivityResponse>> getAllActivities() {
        return ResponseEntity.ok(service.findAllActivities());
    }

    @GetMapping("/connectedUser")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByConnectedUserId(
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.findActivitiesByUserId(connectedUser));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById(
            @PathVariable Long activityId
    ) {
        return ResponseEntity.ok(service.findById(activityId));
    }

    @GetMapping("/availableTimeSet/{date}")
    public ResponseEntity<List<LocalTime>> getAvailableTimesForEmployee(
            @PathVariable LocalDate date,
            @RequestParam Long id
    ) {
        return ResponseEntity.ok(service.findAvailableTimesForEmployee(id, date));
    }

    @GetMapping("/{employeeId}/{date}")
    public ResponseEntity<List<ActivityResponse>> getListOfActivitiesByEmployeeIdAndDate(
            @PathVariable Long employeeId,
            @PathVariable LocalDate date
    ){
        return ResponseEntity.ok(service.findActivityListForEmployee(employeeId, date));
    }

    @GetMapping("/daysToGo")
    public ResponseEntity<DaysResponse> getDaysToGo(Authentication authentication) {
        try {
            DaysResponse response = service.findNumberOfDays(authentication);
            return ResponseEntity.ok(response);
        } catch (BookingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DaysResponse(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DaysResponse(false, "An unexpected error occurred", null));
        }
    }
}
