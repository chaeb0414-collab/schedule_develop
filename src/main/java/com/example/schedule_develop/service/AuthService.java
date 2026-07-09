package com.example.schedule_develop.service;

import com.example.schedule_develop.dto.LoginResquest;
import com.example.schedule_develop.entity.User;
import com.example.schedule_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public User login(LoginResquest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다."));
        if(!user.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}
