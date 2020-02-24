package com.prozacto.prozacto.Controller;

import com.prozacto.prozacto.Entity.User.Patient;
import com.prozacto.prozacto.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping()
    public Patient addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }
}
