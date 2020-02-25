package com.prozacto.prozacto.service;

import com.prozacto.prozacto.Entity.DocumentPermission;
import com.prozacto.prozacto.converter.DocumentPermissionConverter;
import com.prozacto.prozacto.dao.DocumentPermissionDao;
import com.prozacto.prozacto.model.DocumentPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentPermissionService {

    @Autowired
    DocumentPermissionConverter documentPermissionConverter;

    @Autowired
    DocumentPermissionDao documentPermissionDao;

    public DocumentPermissionDto grantAccess(DocumentPermissionDto documentPermissionDto) {

        Integer clinicId = documentPermissionDto.getClinicId();
        DocumentPermission documentPermission = documentPermissionDao.findDistinctByClinicId(clinicId); // one Permission for Entire Clinic

        if(documentPermission == null)
            documentPermission = documentPermissionConverter.convertModelToEntity(documentPermissionDto);
        else {
            // Extend Access till Appointment Date in case of ReSchedule
            documentPermission.setAccessDate(documentPermissionDto.getAccessDate());
        }

        documentPermission = documentPermissionDao.save(documentPermission);
        return documentPermissionConverter.convertEntityToModel(documentPermission);
    }

    public void deleteAccess(DocumentPermissionDto documentPermissionDto) {

        Integer clinicId = documentPermissionDto.getClinicId();
        Integer doctorId = documentPermissionDto.getDoctorId();

        DocumentPermission documentPermission = documentPermissionDao.findDistinctByDoctorIdAndClinicId(doctorId, clinicId);
        documentPermission.setDeleted(true);
        documentPermissionDao.save(documentPermission);
    }


}
