package com.prozacto.prozacto.Controller;

import com.prozacto.prozacto.Entity.User.User;
import com.prozacto.prozacto.jwtAuth.security.Constants;
import com.prozacto.prozacto.model.UserDto;
import com.prozacto.prozacto.model.UserRequestDto;
import com.prozacto.prozacto.service.CacheService;
import com.prozacto.prozacto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.prozacto.prozacto.jwtAuth.security.Constants.AUTH_HEADER;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CacheService cacheService;

    @Autowired
    HttpServletRequest request;

    @PostMapping("/info")
    public UserDto getUserDetails(@RequestBody UserRequestDto userRequestDto) throws Exception{
        return userService.getDetails(userRequestDto);
    }

    @PostMapping("")
    public UserDto addUser(@RequestBody UserDto userDto) throws Exception{
        return userService.create(userDto);
    }

    @GetMapping("/type")
    public List<UserDto> getUsersByType(@RequestParam("type") Integer userType){
        User user = getUserFromRequest();
        return userService.getUsersByType(userType);
    }

    private User getUserFromRequest(){
        User authUser = (User) request.getAttribute(Constants.AUTH_USER);
        return authUser;
    }
}
