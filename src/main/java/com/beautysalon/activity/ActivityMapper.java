package com.beautysalon.activity;

import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.activity.dto.ActivityResponse;
import com.beautysalon.file.FileUtils;
import com.beautysalon.user.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;


@Component
public class ActivityMapper {

    public Activity map(ActivityRequest request){
        return Activity.builder()
                .date(request.getDate())
                .startTime(request.getStartTime())
                .remarks(request.getRemarks())
                .deposit(request.getDeposit())
                .depositPaid(request.isDepositPaid())
                .taskDone(request.isTaskDone())
                .userEntityList(new ArrayList<>())
                .build();
    }
    public ActivityResponse map(Activity activity) {

        return ActivityResponse.builder()
                .id(activity.getId())
                .date(activity.getDate())
                .startTime(activity.getStartTime())
                .finishTime(getFinishTime(activity.getStartTime(), activity.getServiceEntity().getDuration()))
                .remarks(activity.getRemarks())
                .serviceName(activity.getServiceEntity().getName())
                .activityImage(FileUtils.readFileFromLocation(activity.getServiceEntity().getImage()))
                .userIds(activity.getUserEntityList().stream().map(UserEntity::getUserId).toList())
                .taskDone(activity.isTaskDone()).deposit(activity.getDeposit()).depositPaid(activity.isDepositPaid()).build();
    }

    private LocalTime getFinishTime(LocalTime startTime, LocalTime duration){
        return startTime.minusHours(duration.getHour()).minusMinutes(duration.getMinute()).minusSeconds(duration.getSecond());
    }
}
