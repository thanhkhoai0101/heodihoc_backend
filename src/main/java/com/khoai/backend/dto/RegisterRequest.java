package com.khoai.backend.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterRequest {

    private String username;
    private String password;
    private String fullName;
    private LocalDate birthday;
    private String address;

}
