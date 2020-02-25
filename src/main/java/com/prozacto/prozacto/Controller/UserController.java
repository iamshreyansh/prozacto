package com.prozacto.prozacto.Controller;

import com.prozacto.prozacto.model.UserDto;
import com.prozacto.prozacto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new String("Sinayak Wing");
    }

    @PostMapping("")
    public UserDto addUser(@RequestBody UserDto userDto){
        return userService.create(userDto);
    }


}
