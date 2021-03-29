package com.belazy.basics.auth.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author tangcp
 */
@Data
public class UserDetail implements UserDetails {
    private String account;
    private String password;
    private String name;
    private String phone;
    private String realName;

    private Integer enabled;
    private Integer accountNonExpired;
    private Integer accountNonLocked;
    private Integer credentialsNonExpired;
    private List<String> roles;

    public boolean toBoolen(Integer val) {
        return val == 0 ? true : false;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority> ();
        if (roles != null) {
            for (String r : roles) {
                authorityList.add (new SimpleGrantedAuthority (r));
            }
        }
        return authorityList;
    }

    public String getUsername() {
        return account;
    }

    public boolean isAccountNonExpired() {
        return toBoolen (this.accountNonExpired);
    }

    public boolean isAccountNonLocked() {
        return toBoolen (this.accountNonLocked);
    }

    public boolean isCredentialsNonExpired() {
        return toBoolen (this.credentialsNonExpired);
    }

    public boolean isEnabled() {
        return toBoolen (this.enabled);
    }
}
