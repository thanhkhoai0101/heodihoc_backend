package com.khoai.backend.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;;
import java.time.Instant;

@Entity
@Table(name = "user_level_question")
@Getter
@Setter
public class UserLevelQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_question_id")
    private LevelQuestion levelQuestion;

    @Lob
    @Column(name = "answer_config")
    private String answerConfig;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
