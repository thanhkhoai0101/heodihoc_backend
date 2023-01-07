package com.khoai.backend.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "question")
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "type", length = 30)
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    @Lob
    @Column(name = "correct_answer_config")
    private String correctAnswerConfig;

    @Column(name = "point")
    private Integer point;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

}
