package com.prozacto.prozacto.service;

import com.prozacto.prozacto.Entity.User.User;
import com.prozacto.prozacto.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> findAllByUserNameAndType(String userName, Integer type){
        return userDao.findAllByNameLikeAndUserType(userName, type);
    }

}
