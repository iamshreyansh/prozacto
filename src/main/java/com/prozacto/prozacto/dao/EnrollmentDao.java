package com.prozacto.prozacto.dao;

import com.prozacto.prozacto.Entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentDao extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findAllByClinicId(Integer clinicId);
}
