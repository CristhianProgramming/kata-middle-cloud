package com.cristhianpc.kata.management.Utils.implment;

import com.cristhianpc.kata.management.Models.Users;
import com.cristhianpc.kata.management.Services.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserService userService;

    public UserDetailsServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users userFind = userService.getUserByEmail(username);

        if (userFind == null) {
            throw new UsernameNotFoundException("invalid user name");
        }

        List<GrantedAuthority> authorities = Arrays.stream(userFind.getRol()).map(rol -> new SimpleGrantedAuthority(rol.name())).collect(Collectors.toList());

        return new User(userFind.getEmail(), userFind.getPassword(), authorities);
    }

}
