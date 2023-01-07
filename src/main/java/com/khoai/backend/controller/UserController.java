package com.khoai.backend.controller;

import com.khoai.backend.dto.HistoryResponse;
import com.khoai.backend.dto.RegisterRequest;
import com.khoai.backend.dto.RegisterResponse;
import com.khoai.backend.dto.UserResponse;
import com.khoai.backend.model.User;
import com.khoai.backend.model.UserLevel;
import com.khoai.backend.model.UserLevelQuestion;
import com.khoai.backend.repository.LevelRepository;
import com.khoai.backend.repository.UserLevelQuestionRepository;
import com.khoai.backend.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.mapping.Array;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name="User")
public class UserController {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserLevelQuestionRepository userLevelQuestionRepository;
    private final LevelRepository levelRepository;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                          UserLevelQuestionRepository userLevelQuestionRepository,
                          LevelRepository levelRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userLevelQuestionRepository = userLevelQuestionRepository;
        this.levelRepository = levelRepository;
    }

    @GetMapping("")
    public UserResponse getUser(@RequestParam Integer userId){
       var user =  userRepository.findById(userId).get();

       var result= UserResponse.builder()
               .id(user.getId())
               .username(user.getUsername())
               .birthday(user.getBirthday())
               .fullName(user.getFullName())
               .point(user.getPoint())
               .levelId(user.getCurrentLevel().getId())
               .build();
       if (result != null || !result.toString().isEmpty()){
           return result;
       }
       return null;

    }

    @GetMapping("friends")
    public List<UserResponse> listUser(){
        List<UserResponse> result = new ArrayList<>();
        userRepository.findAll().forEach(x->{
            result.add(UserResponse.builder()
                            .point(x.getPoint())
                            .id(x.getId())
                            .levelId(x.getCurrentLevel().getId())
                            .username(x.getUsername())
                            .birthday(x.getBirthday())
                            .fullName(x.getFullName())
                    .build()

            );
        });
       return result;
    }
    @GetMapping("history")
    public List<HistoryResponse> lisHistory(@RequestParam Integer userId){
        List<HistoryResponse> result = new ArrayList<>();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(currentUser.getId());

        userLevelQuestionRepository.findAll().forEach(x->{
                if (x.getUser().getId() == userId)
                    result.add(HistoryResponse.builder()
                                    .userId(userId)
                                    .answer_config(x.getAnswerConfig())
                                    .levelId(x.getLevelQuestion().getLevel().getId())
                                    .point(x.getLevelQuestion().getQuestion().getPoint())
                                    .totalPoint(x.getUser().getPoint())
                            .build());
                }
        );
        return result;
    }

    @PostMapping("register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {

        User user = new User();
        user.setBirthday(request.getBirthday());
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setAddress(request.getAddress());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .address(user.getAddress())
                .birthday(user.getBirthday())
                .build();
    }


}
