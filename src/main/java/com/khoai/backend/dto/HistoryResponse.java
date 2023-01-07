package com.khoai.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HistoryResponse {
    private Integer userId;
    private Integer levelId;
    private String answer_config;
    private Integer point;
    private Integer totalPoint;
}
