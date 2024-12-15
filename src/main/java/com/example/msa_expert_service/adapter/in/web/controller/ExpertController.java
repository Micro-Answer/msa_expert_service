package com.example.msa_expert_service.adapter.in.web.controller;

import com.example.msa_expert_service.adapter.in.web.dto.request.ExpertSignUpRequest;
import com.example.msa_expert_service.adapter.in.web.dto.response.ExpertSignUpResponse;
import com.example.msa_expert_service.application.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b> 전문가 유저 컨트롤러 </b>
 * <p>
 * - 전문가 유저 정보 관리 <br>
 * </p>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ExpertController {
    private final ExpertService expertService;

    @PostMapping("/v1/expert/sign-up")
    public ResponseEntity<ExpertSignUpResponse> signUp(@RequestBody ExpertSignUpRequest body) {
        boolean signUpSuccess = expertService.signUp(body.getUserId(), body.getPw(), body.getRole());

        if (signUpSuccess) {
            return ResponseEntity.ok(new ExpertSignUpResponse("회원가입 성공"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExpertSignUpResponse("회원가입 실패"));
    }
}
