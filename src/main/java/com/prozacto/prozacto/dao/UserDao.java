package com.prozacto.prozacto.dao;

import com.prozacto.prozacto.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

}