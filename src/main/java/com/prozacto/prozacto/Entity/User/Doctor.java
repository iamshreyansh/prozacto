package com.prozacto.prozacto.Entity.User;


import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "user")
@Where(clause = "deleted = 0 AND userType = 1")
public class Doctor extends User {

    @Column(name = "specialization")
    Enum specialization;

    @Column(name = "subSpecialization")
    Enum subSpecialization;

}