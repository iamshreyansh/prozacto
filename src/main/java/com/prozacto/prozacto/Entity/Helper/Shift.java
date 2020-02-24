package com.prozacto.prozacto.Entity.Helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder

@Entity(name = "Shift")
@Where(clause = "deleted = 0")
public class Shift {

    @Column(name = "startTime")
    Date startTime;

    @Column(name = "endTime")
    Date endTime;
}
