package com.onlineLearningPlatform.dto;

import com.onlineLearningPlatform.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleResponseDto {

    private String username;

    private List<UserRole> role;
}
