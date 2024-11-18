package com.example.msa_expert_service;

import com.example.msa_expert_service.adapter.in.web.controller.ExpertController;
import com.example.msa_expert_service.adapter.in.web.dto.request.ExpertSignUpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ExpertController.class)
class ExpertControllerTest {

    @Autowired
    private MockMvc mockMvc;  // MockMvc 객체를 사용하여 HTTP 요청을 모의

    @Autowired
    private ObjectMapper objectMapper;  // JSON 변환을 위한 ObjectMapper

    private ExpertSignUpRequest signUpRequest;

    @BeforeEach
    void setUp() {
        // 테스트에 사용할 ExpertSignUpRequest 객체 초기화
        signUpRequest = new ExpertSignUpRequest();
        signUpRequest.setId("testUser");
        signUpRequest.setPw("password123");
        signUpRequest.setRole("USER");
    }

    @Test
    @DisplayName("전문가 유저 회원가입 요청에 대한 정상 응답 테스트")
    void testSignUp_Success() throws Exception {
        // signUpRequest 객체를 JSON으로 변환
        String jsonRequest = objectMapper.writeValueAsString(signUpRequest);

        // POST 요청을 보내고 응답을 검증
        mockMvc.perform(post("/api/v1/expert/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)  // 요청 타입을 JSON으로 설정
                        .content(jsonRequest))  // 요청 본문에 JSON 데이터 설정
                .andExpect(status().isOk())  // HTTP 상태 코드가 200 OK인지 확인
                .andExpect(jsonPath("$.result").value("message"));  // 응답 본문에서 result가 "message"인지 확인
    }

    @Test
    @DisplayName("회원가입 요청 시 필수 값이 누락된 경우 400 Bad Request 응답 테스트")
    void testSignUp_MissingFields() throws Exception {
        // id를 누락한 요청 객체 설정
        signUpRequest.setId(null);

        // signUpRequest 객체를 JSON으로 변환
        String jsonRequest = objectMapper.writeValueAsString(signUpRequest);

        // POST 요청을 보내고 응답을 검증
        mockMvc.perform(post("/api/v1/expert/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());  // 상태 코드가 400 Bad Request인지 확인
    }

    @Test
    @DisplayName("회원가입 요청 시 id가 비어있는 경우 400 Bad Request 응답 테스트")
    void testSignUp_EmptyId() throws Exception {
        // id를 비어있는 문자열로 설정
        signUpRequest.setId("");

        // signUpRequest 객체를 JSON으로 변환
        String jsonRequest = objectMapper.writeValueAsString(signUpRequest);

        // POST 요청을 보내고 응답을 검증
        mockMvc.perform(post("/api/v1/expert/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());  // 상태 코드가 400 Bad Request인지 확인
    }

    @Test
    @DisplayName("회원가입 요청 시 역할이 누락된 경우 400 Bad Request 응답 테스트")
    void testSignUp_MissingRole() throws Exception {
        // role을 누락한 요청 객체 설정
        signUpRequest.setRole(null);

        // signUpRequest 객체를 JSON으로 변환
        String jsonRequest = objectMapper.writeValueAsString(signUpRequest);

        // POST 요청을 보내고 응답을 검증
        mockMvc.perform(post("/api/v1/expert/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());  // 상태 코드가 400 Bad Request인지 확인
    }
}
