package com.prozacto.prozacto.converter;


import com.prozacto.prozacto.Entity.User.Doctor;
import com.prozacto.prozacto.Entity.User.User;


import com.prozacto.prozacto.model.DoctorDto;
import com.prozacto.prozacto.model.UserDto;
import org.springframework.stereotype.Service;


@Service
public class UserConverter {


    public User convert(UserDto userDto)
    {
        User user = new User();
        user.setId(userDto.getId());
        user.setAge(userDto.getAge());
        user.setContactNumber(userDto.getContactNumber());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        return user;
    }

    public UserDto convert(User user , Doctor doctor)
    {
        UserDto userDto = new UserDto();
        userDto.setAge(user.getAge());
        userDto.setContactNumber(user.getContactNumber());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setUserType(user.getUserType());

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setUserId(user.getId());
        doctorDto.setSpecialization(doctor.getSpecialization());
        doctorDto.setSubSpecialization(doctor.getSubSpecialization());
        userDto.setDoctorDetails(doctorDto);

        return userDto;
    }

    public UserDto convert(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setAge(user.getAge());
        userDto.setContactNumber(user.getContactNumber());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setUserType(user.getUserType());

        return userDto;
    }
}
