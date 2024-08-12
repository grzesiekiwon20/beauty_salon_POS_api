package com.beautysalon.repository.model.booking;


import com.beautysalon.repository.model.user.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


@Getter
@Setter
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue
    @Column(name = "ACTIVITY_ID")
    private Integer id;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "START_TIME")
    private LocalTime startTime;
    @Column(name = "FINISH_TIME")
    private LocalTime finishTime;
    @Column(name = "SERVICE_TYPE")
    private ServiceType serviceType;
    @Column(name = "USER_TYPE")
    private UserType userType;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "TASK_DONE")
    private boolean taskDone;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Activity booking = (Activity) o;
        return getId() != null && Objects.equals(getId(), booking.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}