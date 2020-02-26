package com.prozacto.prozacto.jwtAuth.service;

import com.google.common.cache.Cache;
import com.prozacto.prozacto.Entity.User.User;
import com.prozacto.prozacto.dao.UserDao;
import com.prozacto.prozacto.jwtAuth.exception.CustomException;
import com.prozacto.prozacto.jwtAuth.model.Role;
import com.prozacto.prozacto.jwtAuth.security.JwtTokenProvider;
import com.prozacto.prozacto.jwtAuth.utils.Hashing;
import com.prozacto.prozacto.model.enums.UserType;
import com.prozacto.prozacto.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthUserService {

    @Autowired
    private UserDao userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CacheService cacheService;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userRepository.findByName(username);
            List<Role> userRoles = getUserRoles(user);
            String jwtToken = jwtTokenProvider.createToken(username, userRoles);
            cacheService.setUser(jwtToken, user);
            return jwtToken;
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied" , HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (!userRepository.existsByName(user.getUsername())) {
            user.setPassword(Hashing.getEncoder().encode(user.getPassword()));
            user = userRepository.save(user);
            List<Role> roles = getUserRoles(user);
            return jwtTokenProvider.createToken(user.getUsername(), roles);
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public List<Role> getUserRoles(User user) {
        List<Role> roleList = new ArrayList<>();
        Integer userType = user.getUserType();

        if (userType == UserType.PATIENT.getId()) {
            roleList.add(Role.PATIENT);
        } else if (userType == UserType.DOCTOR.getId()) {
            roleList.add(Role.DOCTOR);
        } else if (userType == UserType.ADMIN.getId()) {
            roleList.add(Role.ADMIN);
        } else {
            log.info("Un Identified Role : {}", userType);
        }
        return roleList;
    }

    public User search(String username) {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByName(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByName(username).getRoles());
    }

}