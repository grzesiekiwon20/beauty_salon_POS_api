package com.beautysalon.activity;

import com.beautysalon.activity.dto.ActivityRequest;
import com.beautysalon.activity.dto.ActivityResponse;
import com.beautysalon.file.FileUtils;
import org.springframework.stereotype.Component;


@Component
public class ActivityMapper {

    public Activity map(ActivityRequest request){
        Activity activity = new Activity();
        activity.setDate(request.date());
        activity.setStartTime(request.startTime());
        activity.setRemarks(request.remarks());
        activity.setDeposit(request.deposit());
        activity.setDepositPaid(request.depositPaid());
        return activity;
    }
    public ActivityResponse map(Activity activity) {
        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setDate(activity.getDate());
        activityResponse.setStartTime(activity.getStartTime());
        activityResponse.setFinishTime(activity.getFinishTime());
        activityResponse.setRemarks(activity.getRemarks());
        activityResponse.setTaskDone(activity.isTaskDone());
        activityResponse.setDeposit(activity.getDeposit());
        activityResponse.setDepositPaid(activity.isDepositPaid());
        activityResponse.setEmployee(activity.getEmployee());
        activityResponse.setCustomer(activity.getCustomer());
        activityResponse.setServiceName(activity.getService().getName());
        activityResponse.setActivityImage(FileUtils.readFileFromLocation(activity.getService().getImage()));
        return activityResponse;
    }
}
