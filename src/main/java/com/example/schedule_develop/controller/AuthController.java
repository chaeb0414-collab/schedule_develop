package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.LoginResquest;
import com.example.schedule_develop.entity.User;
import com.example.schedule_develop.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody LoginResquest request,
            HttpSession session
            ){
        User user = authService.login(request);
        session.setAttribute("LOGIN_user", user.getId());
        return ResponseEntity.ok(
                Map.of("message","로그인 성공")
        );
    }
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(
            HttpSession session
    ){
        session.invalidate();

        return ResponseEntity.ok(
                Map.of("massage","로그아웃 성공")
        );
    }
}


