package com.prozacto.prozacto.Entity.Clinic;

import com.prozacto.prozacto.Entity.BaseEntityIntID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "clinic")
@Where(clause = "deleted = 0")
public class Clinic extends BaseEntityIntID {

    @Column(name = "name")
    private String name;

    @Column(name = "location_id")
    private Integer locationId;

}
