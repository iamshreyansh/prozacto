
package com.prozacto.prozacto.Entity.User;

import com.prozacto.prozacto.Entity.BaseEntityIntID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Where(clause = "deleted = 0")
public class User extends BaseEntityIntID {

    @Column(name = "username")
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "contact_number")
    String contactNumber;
}