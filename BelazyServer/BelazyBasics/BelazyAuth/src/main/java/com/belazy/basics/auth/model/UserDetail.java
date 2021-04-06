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
    private static final long serialVersionUID = 1L;
    private String userId;
    private String account;
    private String password;
    private String name;
    private String phone;
    private String nickname;

    private Integer isAccountEnabled;
    private Integer isAccountExpired;
    private Integer isAccountLocked;
    private Integer isCredentialsExpired;
    private List<String> roles;

    public boolean toBoolen(Integer val) {
        return val == 0 ? true : false;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority> ();
        roles = new ArrayList<> ();
        roles.add ("AAA");
        roles.add ("BBB");
        roles.add ("CCC");
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
        return toBoolen (this.isAccountExpired);
    }

    public boolean isAccountNonLocked() {
        return toBoolen (this.isAccountLocked);
    }

    public boolean isCredentialsNonExpired() {
        return toBoolen (this.isCredentialsExpired);
    }

    public boolean isEnabled() {
        return toBoolen (this.isAccountEnabled);
    }
}
