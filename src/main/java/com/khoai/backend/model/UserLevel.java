package com.khoai.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "user_level")
@Getter
@Setter
public class UserLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level;

    @Column(name = "status", length = 30)
    @Enumerated(EnumType.STRING)
    private UserLevelStatus status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_level_question_id")
    private LevelQuestion currentLevelQuestion;



}
