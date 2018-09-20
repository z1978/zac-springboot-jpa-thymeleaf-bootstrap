package com.zac.spring.web.dto;

import com.zac.spring.customAnnotations.ValidRoleName;

import lombok.Data;

/**
 * Created by Keno&Kemo on 08.12.2017..
 */
@Data
public class RoleDto {
    Long id;
    @ValidRoleName
    String name;
}
