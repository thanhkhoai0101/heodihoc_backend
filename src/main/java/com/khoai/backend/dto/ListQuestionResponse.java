package com.khoai.backend.dto;


import com.khoai.backend.model.Answer;
import com.khoai.backend.model.QuestionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ListQuestionResponse {
    private Integer id;
    private String questionContent;
    private List<AnswerResponse> answers;
    private QuestionType type;
    private String correctAnswerConfig;
    private Integer point;
}
