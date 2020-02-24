package com.prozacto.prozacto.Controller;

import java.util.concurrent.atomic.AtomicLong;

import com.prozacto.prozacto.Entity.User.User;
import com.prozacto.prozacto.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    UserDao userDao;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new String("Sinayak Wing");
    }

    @PostMapping("")
    public User addUser(@RequestBody User user){
        return userDao.save(user);
    }


}
