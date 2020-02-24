package com.prozacto.prozacto.dao;

import com.prozacto.prozacto.Entity.User.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Integer> {
}
