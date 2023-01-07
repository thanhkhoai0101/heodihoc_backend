package com.khoai.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnswerResponse {
    private Integer id;
    private String content;
}
