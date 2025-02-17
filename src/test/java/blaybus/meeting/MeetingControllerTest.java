package blaybus.meeting;

import blaybus.domain.meeting.infra.feignclient.GoogleMeetClient;
import blaybus.domain.meeting.infra.feignclient.dto.request.ConferenceRequest;
import blaybus.domain.meeting.infra.feignclient.dto.response.ConferenceData;
import blaybus.domain.meeting.infra.feignclient.dto.response.ConferenceResponse;
import blaybus.domain.meeting.presentation.dto.request.MeetingCreateRequest;
import blaybus.domain.oauth2.application.service.GoogleAccessTokenAndRefreshTokenService;
import blaybus.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import blaybus.domain.user.domain.entity.Role;
import blaybus.global.jwt.domain.entity.GoogleJsonWebToken;
import blaybus.global.jwt.domain.repository.GoogleJsonWebTokenRepository;
import blaybus.global.jwt.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MeetingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GoogleJsonWebTokenRepository tokenRepository;


    @MockBean  // GoogleMeetClient를 Mock으로 대체
    private GoogleMeetClient googleMeetClient;

    @MockBean  // 추가
    private GoogleAccessTokenAndRefreshTokenService tokenService;


    @BeforeEach
    void setup() {
        // GoogleMeetClient의 응답을 Mock
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
                anyString(),  // 아무 문자열
                any(ConferenceRequest.class)  // 아무 ConferenceRequest 객체
        )).thenReturn(mockResponse);

        // 토큰 갱신 Mock 추가
        OAuth2TokenResponse mockTokenResponse = new OAuth2TokenResponse(
                "new-access-token",
                "Bearer",
                3600L
        );
        when(tokenService.refreshAccessToken(anyString())).thenReturn(mockTokenResponse);

        // Redis에 테스트용 토큰 저장
        GoogleJsonWebToken testToken = GoogleJsonWebToken.builder()
                .userId("test-user")
                .accessToken("test-access-token")
                .refreshToken("test-refresh-token")
                .expiresIn(LocalDateTime.now().plusHours(1))
                .build();

        tokenRepository.save(testToken);
    }

    private String createTestToken() {
        Role testRole = Role.USER;
        return "Bearer " + jwtUtil.createAccessToken("test-user", testRole, "test@test.com");
    }

    @Test
    void 회의_생성_API_테스트() throws Exception {
        // given
        String token = createTestToken();
        String title = "test-title";
        String designerName = "이초 디자이너";
        LocalDateTime startTime = LocalDateTime.now().plusMinutes(30);
        LocalDateTime endTime = startTime.plusHours(1);
        MeetingCreateRequest request = new MeetingCreateRequest(title, startTime, endTime, designerName);

        // when & then
        mockMvc.perform(post("/api/meetings")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                        .andExpect(status().isCreated());
    }

    @Test
    void 회의_생성_인증_실패_테스트() throws Exception {
        // given
        String title = "test-title";
        LocalDateTime startTime = LocalDateTime.now().plusMinutes(5);
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        String designerName = "이초 디자이너";
        MeetingCreateRequest request = new MeetingCreateRequest(title, startTime, endTime, designerName);

        // when & then
        mockMvc.perform(post("/api/meetings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());  // 토큰 없이 요청
    }
}
