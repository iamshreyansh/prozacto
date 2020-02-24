package com.prozacto.prozacto.service;

import com.prozacto.prozacto.Entity.Enrollment;
import com.prozacto.prozacto.dao.EnrollmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentDao enrollmentDao;

    public List<Enrollment> findAllByClinicId(Integer clinicId){
        List<Enrollment> enrollments = enrollmentDao.findAllByClinicId(clinicId);
        return enrollments;
    }

}
