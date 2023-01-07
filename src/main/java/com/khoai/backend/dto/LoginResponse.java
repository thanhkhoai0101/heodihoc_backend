package com.khoai.backend.dto;

import com.khoai.backend.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    private String token;
    private User user;
}
