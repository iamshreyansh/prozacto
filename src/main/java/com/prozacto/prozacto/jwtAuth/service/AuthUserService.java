package com.prozacto.prozacto.jwtAuth.service;

import com.prozacto.prozacto.Entity.User.User;
import com.prozacto.prozacto.dao.UserDao;
import com.prozacto.prozacto.jwtAuth.exception.CustomException;
import com.prozacto.prozacto.jwtAuth.model.Role;
import com.prozacto.prozacto.jwtAuth.security.JwtTokenProvider;
import com.prozacto.prozacto.jwtAuth.utils.Hashing;
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

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByName(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied" , HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (!userRepository.existsByName(user.getUsername())) {
            user.setPassword(Hashing.getEncoder().encode(user.getPassword()));
            userRepository.save(user);
            List<Role> roles = getUserRoles(user);
            return jwtTokenProvider.createToken(user.getUsername(), roles);
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private List<Role> getUserRoles(User user) {
        List<Role> roleList = new ArrayList<>();
        for(String role : user.getRolesList())
        {
            switch (role){
                case "PATIENT": roleList.add(Role.PATIENT); break;
                case "DOCTOR": roleList.add(Role.DOCTOR); break;
                case "ADMIN": roleList.add(Role.ADMIN); break;
                default:
                    log.info("Un Identified Role : {}", role);
            }
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