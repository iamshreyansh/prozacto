package com.prozacto.prozacto.Entity.User;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Where(clause = "deleted = 0 AND userType = 2")
public class Patient extends User {

    @Column(name = "age")
    Integer age;
}




