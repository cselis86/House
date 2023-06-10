package com.houseproject.house.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "u_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

        @Id
        @SequenceGenerator(
                name = "user_id_sequence",
                sequenceName = "user_id_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "user_id_sequence"
        )
        @Column(name = "user_id")
        private Long id;

        private String name;

        @Column(unique = true)
        private String username;

        private String password;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "user_role_junction",
                joinColumns = {@JoinColumn(name = "user_id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id")}
        )
        private Set<Role> authorities;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
        }

        @Override
        public String getPassword() {
                return password;
        }

        @Override
        public String getUsername() {
                return username;
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }
}