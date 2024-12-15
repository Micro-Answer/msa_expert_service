package com.example.msa_expert_service.adapter.out.db;

import org.springframework.stereotype.Component;

/**
 * <b> 전문가 유저 영속성 어댑터 </b>
 */
@Component
public class PersistenceAdapter {

    /**
     * <b> 전문가 유저의 추가 정보 저장 </b>
     * <p>
     * - 현재는 회원에 대한 추가 정보 없음
     * - 이메일과 나이는 예시 데이터
     * </p>
     *
     * @param userId 유저 아이디
     * @param email 예시 데이터
     * @param age 예시 데이터
     */
    public void saveExpertUser(String userId, String email, int age) {
        // 데이터베이스 저장 예시
        System.out.printf("저장된 데이터: 아이디 = %s, 이메일 = %s, 나이 = %d \n", userId, email, age);
    }
}
