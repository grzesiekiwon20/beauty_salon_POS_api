package com.beautysalon.user;


import com.beautysalon.activity.Activity;
import com.beautysalon.address.Address;
import com.beautysalon.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    private String username;
    private String password;
    private String fullName;
    private String phone;

    private String email;
    private boolean enabled;
    private boolean locked;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles ;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))

    private Set<Address> addresses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_activity",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    @JsonIgnore
    private List<Activity> activities;


    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }


    @Override
    @NonNull
    public String getPassword() {
        return this.password;
    }

    @Override
    @NonNull
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
