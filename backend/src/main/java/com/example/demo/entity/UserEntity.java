package com.example.demo.entity;

import java.util.Collection;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="Users")
@Data
@AllArgsConstructor
@SqlResultSetMapping(
        name = "UsersMapping",
        classes = @ConstructorResult(
            	targetClass = Users.class,
            columns = { 
                        @ColumnResult(name = "id", type = String.class), 
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "password", type = String.class),
                        @ColumnResult(name = "role", type = String.class)
            	}
        	))
public class UserEntity implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String password;
	
    @Transient
    private Collection<SimpleGrantedAuthority> authorities;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.name;
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
    
    public enum Role implements GrantedAuthority {
        NORMAL,
        ADMIN;

        @Override
        public String getAuthority() {
            return "ROLE_" + this.name();
        }
    }
}