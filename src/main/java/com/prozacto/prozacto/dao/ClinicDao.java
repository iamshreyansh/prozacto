package com.prozacto.prozacto.dao;


import com.prozacto.prozacto.Entity.Clinic.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicDao extends JpaRepository<Clinic, Integer> {

}