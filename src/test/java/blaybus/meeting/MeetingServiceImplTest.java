package blaybus.meeting;

import blaybus.domain.meeting.application.service.MeetingService;
import blaybus.domain.meeting.infra.feignclient.GoogleMeetClient;
import blaybus.domain.meeting.infra.feignclient.dto.request.ConferenceRequest;
import blaybus.domain.meeting.infra.feignclient.dto.response.ConferenceData;
import blaybus.domain.meeting.infra.feignclient.dto.response.ConferenceResponse;
import blaybus.domain.meeting.presentation.dto.request.MeetingCreateRequest;
import blaybus.domain.meeting.presentation.dto.response.MeetingResponse;
import blaybus.domain.oauth2.application.service.GoogleAccessTokenAndRefreshTokenService;
import blaybus.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import blaybus.global.jwt.domain.entity.GoogleJsonWebToken;
import blaybus.global.jwt.domain.repository.GoogleJsonWebTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class MeetingServiceImplTest {

    @MockBean  // GoogleMeetClient를 모킹
    private GoogleMeetClient googleMeetClient;

    @MockBean
    private GoogleAccessTokenAndRefreshTokenService tokenService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private GoogleJsonWebTokenRepository tokenRepository;  // 레포지토리 주입

    @BeforeEach
    void setup() {
        // 테스트용 토큰 데이터 생성
        GoogleJsonWebToken testToken = GoogleJsonWebToken.builder()
                .userId("test-user")
                .accessToken("test-access-token")
                .refreshToken("test-refresh-token")
                .expiresIn(LocalDateTime.now().plusHours(1))
                .build();

        tokenRepository.save(testToken);

        // GoogleMeetClient 응답 모킹
        ConferenceResponse mockResponse = new ConferenceResponse(
                "test-id",                         // id
                "https://meet.google.com/test-link", // hangoutLink
                new ConferenceData(                // conferenceData
                        "test-conf-id",
                        null,     // conferenceSolution
                        null,     // createRequest
                        null      // entryPoints
                )
        );

        when(googleMeetClient.createMeeting(
                anyString(),
                any(ConferenceRequest.class)
        )).thenReturn(mockResponse);

        // 토큰 갱신 Mock
        OAuth2TokenResponse mockTokenResponse = new OAuth2TokenResponse(
                "new-access-token",
                "Bearer",
                3600L
        );
        when(tokenService.refreshAccessToken(anyString())).thenReturn(mockTokenResponse);
    }

    @Test
    void 회의_생성_테스트() {

        // given
        String userId = "test-user";
        String title = "test-title";

        LocalDateTime startTime = LocalDateTime.now().plusMinutes(5);
        LocalDateTime endTime = startTime.plusHours(1);
        String designerName = "이초 디자이너";
        MeetingCreateRequest request = new MeetingCreateRequest(title, startTime, endTime, designerName);

        /**
        // when
        MeetingResponse response = meetingService.createMeeting(userId, request);

        // then
        assertNotNull(response.hangoutLink());
        assertTrue(response.title().contains("상담 예약"));

        System.out.println(response.title());
        System.out.println(response.hangoutLink());
        System.out.println("회의 종료 시간: " + request.endTime());

         */
    }
}
