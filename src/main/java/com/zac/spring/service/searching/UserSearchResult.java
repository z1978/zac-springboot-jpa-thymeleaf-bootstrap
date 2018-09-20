package com.zac.spring.service.searching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import com.zac.spring.web.dto.UserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchResult {
    private Page<UserDto> userDtoPage;
    private boolean hasNumberFormatException;
}
