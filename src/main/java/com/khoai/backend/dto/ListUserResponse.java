package com.khoai.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListUserResponse {
    private Integer id;
    private String userName;
    private String level;
    private Integer point;
}
