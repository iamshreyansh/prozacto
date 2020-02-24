package com.prozacto.prozacto.service;

import com.prozacto.prozacto.Entity.User.Patient;
import com.prozacto.prozacto.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PatientService {

    @Autowired
    PatientDao patientDao;

    public Patient addPatient(@RequestBody Patient patient){
        return patientDao.save(patient);
    }
}
