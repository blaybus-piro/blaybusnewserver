package blaybus.domain.metting.application.service;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

@Service
public class GoogleOAuthService {

    @Value("${google.api.service-account-key-path}")
    private String serviceAccountKeyPath;

    // 위임할 사용자의 이메일 (예: 캘린더 소유자)
//    @Value("${google.api.delegated-user-email}")
    private String delegatedUserEmail="blaybus@feelbuddy-432315.iam.gserviceaccount.com";

    /**
     * Service Account JSON 키 파일을 사용하여, 위임된 사용자 캘린더에 접근하기 위한 OAuth 토큰 발급
     */
    public String getAccessToken() throws IOException {
        GoogleCredentials credentials = ServiceAccountCredentials
                .fromStream(new FileInputStream(serviceAccountKeyPath))
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/calendar"));

        // 도메인-wide delegation을 통해 캘린더 소유자(사용자)로 위임
        credentials = ((ServiceAccountCredentials) credentials).createDelegated(delegatedUserEmail);

        credentials.refreshIfExpired();
        return credentials.getAccessToken().getTokenValue();
    }
}