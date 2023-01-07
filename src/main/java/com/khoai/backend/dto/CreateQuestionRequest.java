package com.khoai.backend.dto;

import com.khoai.backend.model.QuestionType;
import lombok.Setter;
import lombok.Getter;

import java.util.List;

@Setter
@Getter
public class CreateQuestionRequest {
    private String content;
    private QuestionType type;
    private List<String> answers;
    private List<Integer> correctIndexes;
    private Integer point;
}
