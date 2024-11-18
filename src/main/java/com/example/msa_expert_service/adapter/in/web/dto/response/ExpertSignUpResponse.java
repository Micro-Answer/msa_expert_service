package com.example.msa_expert_service.adapter.in.web.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b> 역할: 전문가 유저 회원가입 응답 </b>
 * <p>
 * - HTTP 응답 바디에 매핑 <br>
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
public class ExpertSignUpResponse {
    private String result;

    public ExpertSignUpResponse(String result) {
        this.result = result;
    }
}
