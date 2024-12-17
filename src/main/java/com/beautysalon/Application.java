package com.beautysalon;


import com.beautysalon.activity.ActivityMapper;
import com.beautysalon.activity.ActivityRepository;
import com.beautysalon.activity.ActivityService;
import com.beautysalon.config.KeycloakAdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ActivityRepository activityRepository, ActivityService activityService, ActivityMapper mapper, KeycloakAdminService service) {
        return args -> {
//            Long id = 1L;
//            List<ActivityResponse> activityResponseList = activityRepository.findActivitiesByEmployeeId(id).stream().map(mapper::map).toList();
//
//           HashSet<LocalTime> map = activityService.findAvailableTimesOfDay(activityResponseList);
//            System.out.println(map);
//            List<UserRepresentation> userRepresentations = service.getUsers();
//            for (UserRepresentation user : userRepresentations){
//                System.out.println(user.getAttributes());
//            }

//            service.getUsers().
        };
    }

}
