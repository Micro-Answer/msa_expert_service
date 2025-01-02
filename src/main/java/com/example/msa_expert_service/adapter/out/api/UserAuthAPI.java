package com.example.msa_expert_service.adapter.out.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * <b> 회원관리 API 요청 </b>
 */
@Component
public class UserAuthAPI {

    /**
     * <b> 회원가입을 외부 서비스에 요청 </b>
     * <p>
     * - 추후 응답 코드와 바디에 대한 로깅 추가
     * </p>
     *
     * @param userId 유저 아이디
     * @param pw 비밀번호
     * @param role 역할
     * @return 외부 API 응답
     * @throws IOException ObjectMapper의 writeValueAsString에 의해 발생
     * @throws InterruptedException HttpClient의 send에 의해 발생
     */
    public HttpResponse<String> requestUserSignUp(String userId, String pw, String role) throws IOException, InterruptedException {
        final var objectMapper = new ObjectMapper();
        final var client = HttpClient.newHttpClient();
        final var uri = URI.create("http://localhost:8081/api/v1/user/sign-up");
        final var jsonBody = objectMapper.writeValueAsString(new SignUpDTO(userId, pw, role));

        final var request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
