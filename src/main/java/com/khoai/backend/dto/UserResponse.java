package com.khoai.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
public class UserResponse {
    private Integer id;
    private String username;
    private String fullName;
    private LocalDate birthday;
    private Integer levelId;
    private Integer point;
}
