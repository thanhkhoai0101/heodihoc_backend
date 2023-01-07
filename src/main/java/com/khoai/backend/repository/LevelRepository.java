package com.khoai.backend.repository;

import com.khoai.backend.model.Level;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface LevelRepository extends PagingAndSortingRepository<Level, Integer> {
}
