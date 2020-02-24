package com.prozacto.prozacto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    private UserDto userDto;
    private Integer id;
    private SpecializationEnum specialization;
    private SubSpecializationEnum subSpecialization;
}
