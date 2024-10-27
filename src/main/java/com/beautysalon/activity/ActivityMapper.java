package com.beautysalon.activity;

import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.activity.dto.ActivityResponse;
import org.springframework.stereotype.Component;


@Component
public class ActivityMapper {

    public Activity map(ActivityRequest request){
        return Activity.builder()
                .date(request.date())
                .startTime(request.startTime())
                .finishTime(request.finishTime())
                .remarks(request.remarks())
                .taskDone(request.taskDone())
                .deposit(request.deposit())
                .depositPaid(request.depositPaid())
                .employeeId(request.employeeId())
                .userId(request.userId())
                .typeId(request.typeId())
                .build();
    }
    public ActivityResponse map(Activity activity) {
        return ActivityResponse.builder()
                .id(activity.getId())
                .date(activity.getDate())
                .startTime(activity.getStartTime())
                .finishTime(activity.getFinishTime())
                .remarks(activity.getRemarks())
                .taskDone(activity.isTaskDone())
                .deposit(activity.getDeposit())
                .depositPaid(activity.isDepositPaid())
                .employeeId(activity.getEmployeeId())
                .userId(activity.getUserId())
                .typeId(activity.getTypeId())
                .build();
    }
}
