package baimuhtar.shop.security;

import baimuhtar.shop.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    public UserDetailsImpl (User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String roleName = "ROLE_" + user.getRole().getServiceName().toLowerCase();
        return Collections.singleton(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
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
