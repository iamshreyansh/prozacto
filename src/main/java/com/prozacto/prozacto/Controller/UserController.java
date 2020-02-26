package com.prozacto.prozacto.Controller;

import com.prozacto.prozacto.model.UserDto;
import com.prozacto.prozacto.model.UserRequestDto;
import com.prozacto.prozacto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/info")
    public UserDto getUserDetails(@RequestBody UserRequestDto userRequestDto) throws Exception{
        return userService.getDetails(userRequestDto);
    }

    @PostMapping("")
    public UserDto addUser(@RequestBody UserDto userDto) throws Exception{
        return userService.create(userDto);
    }

    @GetMapping("/type")
    public List<UserDto> getUsersByType(@RequestParam("type") Integer userType)
    {
        return userService.getUsersByType(userType);
    }

}
