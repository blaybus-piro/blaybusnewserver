package blaybus.meeting;

import blaybus.domain.meeting.infra.feignclient.GoogleMeetClient;
import blaybus.domain.meeting.infra.feignclient.dto.request.ConferenceRequest;
import blaybus.domain.meeting.infra.feignclient.dto.response.ConferenceResponse;
import blaybus.global.jwt.util.JWTUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 실제 서버 포트로 테스트
public class MeetingIntegrationTest {

    @Autowired
    private GoogleMeetClient googleMeetClient; // FeignClient 주입

    @Autowired
    private JWTUtil jwtUtil;


    @Test
    void Google_Meet_생성_테스트() {
        // given
        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJjYXRlZ29yeSI6ImFjY2VzcyIsImlkIjoiMTAxMzM1NzMxMjU5MTE1MzA2NDMzIiwicm9sZSI6IlVTRVIiLCJlbWFpbCI6ImtpbW1pbnNvbzExNkBnbWFpbC5jb20iLCJpYXQiOjE3Mzk2MDE1MjUsImV4cCI6MTczOTYwMjQyNX0.4ZBYiLsUMFUITaUafmhKfYIiZn2-EwbtnBOLGfGldRk";
        String token = "Bearer " + accessToken;
        String title = "test-title";
        LocalDateTime startTime = LocalDateTime.now().plusMinutes(5);

        ConferenceRequest request = new ConferenceRequest();
        request.setTitle(title);

        // 시작 시간 설정
        ConferenceRequest.EventDateTime start = new ConferenceRequest.EventDateTime();
        start.setDateTime(startTime.toString());
        request.setStart(start);

        // 종료 시간 설정
        ConferenceRequest.EventDateTime end = new ConferenceRequest.EventDateTime();
        end.setDateTime(startTime.plusHours(1).toString());
        request.setEnd(end);

        // when
        ConferenceResponse response = googleMeetClient.createMeeting(token, request);

        // then
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getHangoutLink());
        System.out.println("생성된 링크: " + response.getHangoutLink());
    }

}
