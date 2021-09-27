package com.murilonerdx.restapispring.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String username;
    private String fullName;
    private String password;
    @Column(name="account_non_expired")
    private boolean accountNonExpired;
    @Column(name="account_non_locked")
    private boolean isAccountNonLocked;
    @Column(name="credentials_non_expired")
    private boolean isCredentialsNonExpired;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission", joinColumns = {
            @JoinColumn(name = "id_user")
    }, inverseJoinColumns = {
            @JoinColumn(name = "id_permission")
    })
    private List<Permission> permissions;

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Permission permission : this.permissions
        ) {
            roles.add(permission.getDescription());
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }
}
