package com.prozacto.prozacto.service;

import com.prozacto.prozacto.Entity.Clinic;
import com.prozacto.prozacto.converter.ClinicConverter;
import com.prozacto.prozacto.dao.ClinicDao;
import com.prozacto.prozacto.model.ClinicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClinicService {

    @Autowired private ClinicDao clinicDao;
    @Autowired private ClinicConverter clinicConverter;

    public ClinicDto getClinicById(Integer id)
    {
        Optional<Clinic> clinic = clinicDao.findById(id);
        if(clinic.isPresent())
            return clinicConverter.convertEntityToModel(clinic.get());
        else
            return null;
    }
}
