package com.khoai.backend.controller;

import com.khoai.backend.dto.ListLevelResponse;
import com.khoai.backend.model.Level;
import com.khoai.backend.model.User;
import com.khoai.backend.model.UserLevelStatus;
import com.khoai.backend.repository.LevelRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/level")
@Tag(name = "Level")
public class LevelController {
    private final LevelRepository levelRepository;

    public LevelController(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @GetMapping("")
    public List<ListLevelResponse> listLevel() {
        List<ListLevelResponse> result = new ArrayList<>();

        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("Current user la");
        System.out.println(currentUser.getFullName());

        levelRepository.findAll().forEach(x -> {
            result.add(ListLevelResponse.builder()
                    .id(x.getId())
                    .status(UserLevelStatus.NEW)
                    .description(x.getDescription())
                    .name(x.getName())
                    .build());
        });

        return result;
    }

    @PostMapping("")
    public Level create(@RequestBody Level level) {
        return levelRepository.save(level);
    }

}
