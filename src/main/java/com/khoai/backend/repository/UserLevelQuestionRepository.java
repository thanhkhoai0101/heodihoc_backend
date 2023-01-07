package com.khoai.backend.repository;

import com.khoai.backend.model.UserLevelQuestion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserLevelQuestionRepository extends CrudRepository<UserLevelQuestion, Integer> {
    List<UserLevelQuestion> findAllByUser(Integer userId);
}
