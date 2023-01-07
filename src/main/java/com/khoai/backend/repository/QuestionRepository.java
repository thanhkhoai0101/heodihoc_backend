package com.khoai.backend.repository;

import com.khoai.backend.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
