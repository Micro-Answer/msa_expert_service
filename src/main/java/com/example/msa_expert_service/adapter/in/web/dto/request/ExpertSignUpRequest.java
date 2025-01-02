package com.example.msa_expert_service.adapter.in.web.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <b> 역할: 전문가 유저 회원가입 요청 </b>
 * <p>
 * - HTTP 요청 바디에 매핑 <br>
 * </p>
 */
@Getter
@Setter
public class ExpertSignUpRequest {
    String userId;
    String pw;
    String role;
}
