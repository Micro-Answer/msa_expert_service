package com.example.msa_expert_service.application.service;

import com.example.msa_expert_service.adapter.out.api.UserAuthAPI;
import com.example.msa_expert_service.adapter.out.db.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <b> 전문가 유저 서비스 </b>
 */
@Service
@RequiredArgsConstructor
public class ExpertService {
    private static final Logger logger = Logger.getLogger(ExpertService.class.getName());

    private final UserAuthAPI userAuthAPI;
    private final PersistenceAdapter persistenceAdapter;

    /**
     * <b> 일반 유저 회원가입 로직 수행 </b>
     *
     * @param userId 유저 아이디
     * @param pw 비밀번호
     * @param role 역할
     * @return 회원가입 성공 여부
     */
    public boolean signUp(String userId, String pw, String role) {
        try {
            // 외부 서비스에 회원가입 요청
            final HttpResponse<String> response = userAuthAPI.requestUserSignUp(userId, pw, role);

            // 외부 서비스 응답 코드에 따라 처리
            if (response.statusCode() == 200 || response.statusCode() == 201) {
                persistenceAdapter.saveExpertUser(userId, "email", 19);
                return true;
            } else {
                logger.log(Level.WARNING, "회원가입 실패, 응답 코드: " + response.statusCode());
                return false;
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "네트워크 오류 발생: " + e.getMessage(), e);
            return false;
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "회원가입 처리 중 인터럽트 발생: " + e.getMessage(), e);
            return false;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "예상치 못한 오류 발생: " + e.getMessage(), e);
            return false;
        }
    }
}