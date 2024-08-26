package com.beautysalon.repository.model;



import com.beautysalon.repository.model.users.User;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@ToString
@NoArgsConstructor
@Table(name = "ACTIVITIES")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long id;

    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "START_TIME")
    private LocalTime startTime;
    @Column(name = "FINISH_TIME")
    private LocalTime finishTime;
    @Column(name = "SERVICE_TYPE")
    private ServiceType serviceType;
    @Column(name = "TASK_DONE")
    private boolean taskDone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "activity_user",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    protected List<User> users;


    public Activity(Long id, LocalDate date, LocalTime startTime, LocalTime finishTime, ServiceType serviceType, boolean taskDone, List<User> users) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.serviceType = serviceType;
        this.taskDone = taskDone;
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return taskDone == activity.taskDone && Objects.equals(id, activity.id) && Objects.equals(date, activity.date) && Objects.equals(startTime, activity.startTime) && Objects.equals(finishTime, activity.finishTime) && serviceType == activity.serviceType && Objects.equals(users, activity.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, startTime, finishTime, serviceType, taskDone, users);
    }
}