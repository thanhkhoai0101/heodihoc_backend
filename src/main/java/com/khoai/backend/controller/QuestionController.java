package com.khoai.backend.controller;

import com.khoai.backend.dto.AnswerResponse;
import com.khoai.backend.dto.ListQuestionResponse;
import com.khoai.backend.model.Answer;
import com.khoai.backend.model.LevelQuestion;
import com.khoai.backend.repository.AnswerRepository;
import com.khoai.backend.repository.LevelQuestionRepository;
import com.khoai.backend.repository.LevelRepository;
import com.khoai.backend.repository.QuestionRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
@Tag(name = "Question")
public class QuestionController {
    private final QuestionRepository questionRepository;

    private final LevelRepository levelRepository;

    public QuestionController(QuestionRepository questionRepository,
                              LevelRepository levelRepository) {
        this.questionRepository = questionRepository;
        this.levelRepository = levelRepository;
    }

    @GetMapping("")
    public List<ListQuestionResponse> listQuestion(@RequestParam Integer levelId) {
        List<ListQuestionResponse> result = new ArrayList<>();


        levelRepository.findById(levelId).get().getLevelQuestions().forEach(x->{
//                List<Answer> answers = x.getQuestion().getAnswers();
//                List<AnswerResponse> answerResponses = new ArrayList<>();
//                for(int i=0;i< answers.size();i++){
//                    answerResponses.add(AnswerResponse.builder()
//                                    .id(answers.get(i).getId())
//                                    .content(answers.get(i).getContent())
//                            .build());
//                }
            result.add(ListQuestionResponse.builder()
                    .id(x.getId())
                        .questionContent(x.getQuestion().getContent())
                        .type(x.getQuestion().getType())
                        .correctAnswerConfig(x.getQuestion().getCorrectAnswerConfig())
                        .point(x.getQuestion().getPoint())
                        .answers(x.getQuestion().getAnswers().stream().map(a-> AnswerResponse.builder()
                                .id(a.getId())
                                .content(a.getContent())
                                .build()).toList())
                        .build()
            );

        });
        return result;
    }

//    @PostMapping("")
//    public Question create(@RequestBody CreateQuestionRequest request) {
//
//        Question question = new Question();
//        question.setContent(request.getContent());
//        question.setPoint(request.getPoint());
//        question.setType(request.getType());
//        question.setAnswers(new ArrayList<>());
//        questionRepository.save(question);
//        List<Answer> savedAnswer = new ArrayList<>() ;
//        for (String content: request.getAnswers()) {
//             Answer answer = new Answer();
//             answer.setQuestion(question);
//             answer.setContent(content);
//
//             answerRepository.save(answer);
//             savedAnswer.add(an)
//         }
//
//    }

}
