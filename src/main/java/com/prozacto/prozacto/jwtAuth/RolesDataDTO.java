package com.prozacto.prozacto.jwtAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesDataDTO {

    private String url;
    private String type;
    private String rolesList;

}