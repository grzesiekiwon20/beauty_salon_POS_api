package com.beautysalon.activity;

import com.beautysalon.activity.dto.ActivityRequest;
import org.springframework.security.core.Authentication;

interface ActivityService {

    void saveActivity(ActivityRequest activityRequest , Authentication authentication);
}
