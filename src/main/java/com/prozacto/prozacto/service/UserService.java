package com.prozacto.prozacto.service;

import com.prozacto.prozacto.Entity.User.Doctor;
import com.prozacto.prozacto.Entity.User.User;
import com.prozacto.prozacto.converter.UserConverter;
import com.prozacto.prozacto.dao.DoctorDao;
import com.prozacto.prozacto.dao.UserDao;
import com.prozacto.prozacto.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    DoctorDao doctorDao;

    @Autowired
    UserConverter userConverter;

    public List<User> findAllByUserNameAndType(String userName, Integer type){
        return userDao.findAllByNameLikeAndUserType(userName, type);
    }

    public UserDto create(UserDto userDto) throws Exception{

        validate(userDto);

        if(userDto.getUserType() == 2)
        {
            User user = userDao.save(userConverter.convert(userDto));

            Doctor doctor = new Doctor();
            doctor.setUserId(user.getId());
            doctor.setSpecialization(userDto.getDoctorDetails().getSpecialization());
            doctor.setSubSpecialization(userDto.getDoctorDetails().getSubSpecialization());
            doctor = doctorDao.save(doctor);

            return userConverter.convert(user,doctor);
        }
        else {
            User user = userDao.save(userConverter.convert(userDto));
            return userConverter.convert(user);
        }


    }

    public void validate(UserDto userDto) throws Exception
    {
        if(userDto.getUserType() == null || userDto.getContactNumber() == null || userDto.getUsername() == null)
            throw new Exception("User Type / Contact number / Username cannot be Null!");

        User user = userDao.findByUsernameOrContactNumber(userDto.getUsername() , userDto.getContactNumber());
        if(user != null)
            throw new Exception("User with given Username or Contact Number already Exists!");
    }

}
