package com.khoai.backend.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegisterResponse {

    private Integer id;
    private String username;
    private String fullName;
    private LocalDate birthday;
    private String address;

}
