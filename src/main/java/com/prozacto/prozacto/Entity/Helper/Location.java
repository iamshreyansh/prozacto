package com.prozacto.prozacto.Entity.Helper;

import com.prozacto.prozacto.Entity.BaseEntityIntID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity(name = "location")
@Where(clause = "deleted = 0")
public class Location extends BaseEntityIntID {

    @Column(name = "latitude")
    Integer latitude;

    @Column(name = "longitude")
    Integer longitude;

    @Column(name = "landmark")
    String landmark;
}
