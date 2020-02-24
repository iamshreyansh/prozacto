package com.prozacto.prozacto.Entity.Appointment;

import com.prozacto.prozacto.Entity.BaseEntityIntID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends BaseEntityIntID {
    Integer clinicId;
    Integer doctorId;
    Integer shiftId;
}
