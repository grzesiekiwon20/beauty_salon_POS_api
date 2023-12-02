package com.beautysalon.repository.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "USERS")
public class Client{

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private List<Booking> bookings;

    public Client(String name, String email, List<Booking> bookings) {
        this.name = name;
        this.email = email;
        this.bookings = bookings;
    }

    public String getDateOfBooking() {
        if(!bookings.isEmpty()) {
            return bookings.get(0).getDate();
        }
        return null;
    }
    public void setDateOfBooking(String newDate) {
        if(!bookings.isEmpty()) {
            bookings.get(0).setDate(newDate);
        }
    }
    public String getStartTimeOfBooking() {
        if(!bookings.isEmpty()) {
            return bookings.get(1).getStartTime();
        }
        return null;
    }
    public void setStartTimeOfBooking(String newStartTime) {
        if(!bookings.isEmpty()) {
            bookings.get(1).setStartTime(newStartTime);
        }
    }
    public String getEndTimeOfBooking() {
        if(!bookings.isEmpty()) {
            return bookings.get(2).getEndTime();
        }
        return null;
    }
    public void setEndTimeOfBooking(String newEndTime) {
        if(!bookings.isEmpty()) {
            bookings.get(2).setEndTime(newEndTime);
        }
    }
    public String getServiceTypeOfBooking() {
        if(!bookings.isEmpty()) {
            return bookings.get(3).getServiceType();
        }
        return null;
    }
    public void setServiceTypeOfBooking(String newServiceType) {
        if(!bookings.isEmpty()) {
            bookings.get(3).setServiceType(newServiceType);
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}
