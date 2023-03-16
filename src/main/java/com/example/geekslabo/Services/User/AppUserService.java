package com.example.geekslabo.Services.User;


import com.example.geekslabo.Entities.*;
import com.example.geekslabo.Repositories.UserRepo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws
            UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUserName(userName);
        Assert.notNull(appUser,
                new UsernameNotFoundException(userName).getMessage());
        return new User(userName
                ,appUser.getPassword()
                ,getAuthorities(appUser.getRole()));
    }


    private List<GrantedAuthority> getAuthorities(UserRole userRole) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userRole.name()));
        return authorities;
    }
}
