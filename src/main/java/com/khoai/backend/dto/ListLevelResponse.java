package com.khoai.backend.dto;

import com.khoai.backend.model.UserLevelStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ListLevelResponse {
    private Integer id;
    private String name;
    private String description;
    private UserLevelStatus status;
}
