package com.prozacto.prozacto.service;

import com.prozacto.prozacto.Entity.Clinic;
import com.prozacto.prozacto.Entity.Enrollment;
import com.prozacto.prozacto.Entity.User.Doctor;
import com.prozacto.prozacto.dao.DoctorDao;
import com.prozacto.prozacto.dao.EnrollmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    DoctorDao doctorDao;

    @Autowired
    EnrollmentDao enrollmentDao;

    public Doctor addDoctor(Doctor doctor){
        return doctorDao.save(doctor);
    }

    public List<Doctor> findDoctorByClinicId(Integer clinicId){
        List<Enrollment> enrollments = enrollmentDao.findAllByClinicId(clinicId);
        List<Integer> doctorIds = enrollments.stream().map(Enrollment::getDoctorId).collect(Collectors.toList());
        return doctorDao.findAllByIdIn(doctorIds);
    }
}
