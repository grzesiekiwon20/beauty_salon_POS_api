package com.beautysalon.role;


import com.beautysalon.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;
}
