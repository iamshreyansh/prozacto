package com.prozacto.prozacto.dao;

import com.prozacto.prozacto.Entity.DocumentPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentPermissionDao extends JpaRepository<DocumentPermission, Integer> {

    DocumentPermission findDistinctByDoctorIdAndClinicId(Integer doctorId, Integer clinicId);
    DocumentPermission findDistinctByClinicId(Integer clinicId);
}
