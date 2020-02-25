package com.prozacto.prozacto.service;

import com.prozacto.prozacto.Entity.Enrollment;
import com.prozacto.prozacto.Entity.TimingShift;
import com.prozacto.prozacto.dao.EnrollmentDao;
import com.prozacto.prozacto.dao.TimingShiftDao;
import com.prozacto.prozacto.model.EnrollmentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Doctor Enrolls with a clinic
 */
@Service
@Slf4j
public class EnrollmentService {

    @Autowired EnrollmentDao enrollmentDao;

    @Autowired
    TimingShiftDao timingShiftDao;

    public Enrollment findById(Integer id) throws Exception {
        if (id == null || id == 0) {
            throw new Exception("Id cannot be null or zero");
        }
        Optional<Enrollment> enrollmentOptional = enrollmentDao.findById(id);
        if (!enrollmentOptional.isPresent()) {
            throw new Exception("Enrollment not present");
        }

        return enrollmentOptional.get();
    }

    // TODO: Check For OverLapping Shifts
    public Enrollment enrollDoctor(EnrollmentDto enrollmentDto) throws Exception {
        Enrollment enrollment = Enrollment.builder()
                                .clinicId(enrollmentDto.getClinicId())
                                .userId(enrollmentDto.getDoctorId())
                                .consultationFee(enrollmentDto.getConsultationFee())
                                .numOfPatients(enrollmentDto.getNumOfPatients())
                                .build();
        enrollment = enrollmentDao.save(enrollment);
        List<TimingShift> timingShiftList = enrollmentDto.getTimingShifts();
        for (TimingShift timingShift : timingShiftList) {
            timingShift.setEnrollmentId(enrollment.getId());
        }
        timingShiftDao.saveAll(timingShiftList);
        return enrollment;
    }

    public List<Enrollment> findAllByClinicId(Integer clinicId) throws Exception {
        return enrollmentDao.findAllByClinicId(clinicId);
    }
}
