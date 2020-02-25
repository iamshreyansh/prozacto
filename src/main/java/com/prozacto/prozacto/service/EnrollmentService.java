package com.prozacto.prozacto.service;

import com.prozacto.prozacto.Entity.Enrollment;
import com.prozacto.prozacto.Entity.TimingShift;
import com.prozacto.prozacto.dao.EnrollmentDao;
import com.prozacto.prozacto.dao.TimingShiftDao;
import com.prozacto.prozacto.model.EnrollmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Doctor Enrolls with a clinic
 */
@Service
@Slf4j
public class EnrollmentService {

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Interval {
        Integer start;
        Integer end;
    }

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

    public Enrollment enrollDoctor(EnrollmentDto enrollmentDto) throws Exception {

        validate(enrollmentDto.getTimingShifts()); //Validate the new shifts
        validate(enrollmentDto); // Check For OverLapping Shifts with existing shifts if any.

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

    public boolean validate(EnrollmentDto enrollmentDto) throws Exception
    {
        Enrollment enrollment = enrollmentDao.findByUserId(enrollmentDto.getDoctorId());

        List<TimingShift> currentTimingShifts = timingShiftDao.findAllByEnrollmentId(enrollment.getId());

        List<Interval> intervals = getIntervals(currentTimingShifts);

        for(TimingShift timingShift : enrollmentDto.getTimingShifts())
        {
            Integer startTime = Integer.parseInt(timingShift.getFromTime().replace(":" , ""));
            Integer endTime = Integer.parseInt(timingShift.getToTime().replace(":" , ""));
            for (Interval interval : intervals)
            {
                Integer start = interval.getStart();
                Integer end = interval.getEnd();
                if(!(startTime < start && endTime < start) || !(startTime > end))
                    throw new Exception("Overlapping Shifts found!");
            }
        }

        return true;
    }

    public boolean validate(List<TimingShift> timingShifts) throws Exception
    {
        for(TimingShift timingShift : timingShifts)
        {
            Integer startTime = Integer.parseInt(timingShift.getFromTime().replace(":" , ""));
            Integer endTime = Integer.parseInt(timingShift.getToTime().replace(":" , ""));
            if(startTime > endTime)
                throw new Exception("Shifts are Wrong(Start time should be less than end time)!");
        }

       List<Interval> intervals = getIntervals(timingShifts);

        for(int i = 0 ; i < intervals.size()-1 ; i++)
        {

            Integer currStart = intervals.get(i).getStart();
            Integer currEnd = intervals.get(i).getEnd();
            Integer nextStart = intervals.get(i+1).getStart();
            Integer nextEnd = intervals.get(i+1).getEnd();
            if(!(currStart < nextStart && currEnd < nextEnd) || !(currStart > nextEnd))
                throw new Exception("Overlapping Shifts found in Dto!");

        }

        return true;
    }

    public List<Interval> getIntervals(List<TimingShift> timingShifts)
    {
        List<Interval> intervals = new ArrayList<>();
        for(TimingShift timingShift : timingShifts)
        {
            Interval interval = new Interval();
            Integer startTime = Integer.parseInt(timingShift.getFromTime().replace(":" , ""));
            Integer endTime = Integer.parseInt(timingShift.getToTime().replace(":" , ""));
            interval.setStart(startTime);
            interval.setEnd(endTime);
            intervals.add(interval);
        }

        Collections.sort(intervals, (m1, m2) -> {
            if(m1.getStart() == m2.getStart())
                return m1.getEnd() - m2.getEnd();
            return m1.getStart()- m2.getStart();
        });
        return intervals;
    }

}
