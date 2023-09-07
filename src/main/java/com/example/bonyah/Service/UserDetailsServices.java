package com.example.bonyah.Service;

import com.example.bonyah.Api.ApiException;
import com.example.bonyah.Models.User;
import com.example.bonyah.Repository.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServices implements UserDetailsService {

    private final AuthRepo authRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepo.logInUsernameOrEmail(username);

        if (user == null) {
            throw new ApiException("wrong username or password");

        }

        return user;
    }
}
