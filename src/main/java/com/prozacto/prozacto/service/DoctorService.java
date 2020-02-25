package com.prozacto.prozacto.service;

import com.prozacto.prozacto.Entity.Enrollment;
import com.prozacto.prozacto.Entity.User.Doctor;
import com.prozacto.prozacto.Entity.User.User;
import com.prozacto.prozacto.dao.DoctorDao;
import com.prozacto.prozacto.model.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    DoctorDao doctorDao;

    @Autowired
    EnrollmentService enrollmentService;

    @Autowired
    UserService userService;

    public Doctor addDoctor(Doctor doctor){
        return doctorDao.save(doctor);
    }

    public List<Doctor> findDoctorsByClinicId(Integer clinicId) throws Exception {
        List<Enrollment> enrollments = enrollmentService.findAllByClinicId(clinicId);
        List<Integer> doctorIds = enrollments.stream().map(Enrollment::getUserId).collect(Collectors.toList());
        return doctorDao.findAllByIdIn(doctorIds);
    }


    public List<Doctor> findByName(String name){

        List<User> users = userService.findAllByUserNameAndType("%"+name+"%", UserType.DOCTOR.getId());
        List<Integer> userIds = users.stream().map(User::getId).collect(Collectors.toList());
        return doctorDao.findAllByIdIn(userIds);
    }
}
