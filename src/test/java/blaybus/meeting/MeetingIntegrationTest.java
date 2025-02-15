package blaybus.meeting;

import blaybus.domain.meeting.infra.feignclient.GoogleMeetClient;
import blaybus.domain.meeting.infra.feignclient.dto.request.ConferenceRequest;
import blaybus.domain.meeting.infra.feignclient.dto.response.ConferenceResponse;
import blaybus.global.jwt.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 실제 서버 포트로 테스트
public class MeetingIntegrationTest {

    @Autowired
    private GoogleMeetClient googleMeetClient; // FeignClient 주입

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    void Google_Meet_생성_테스트() {
        // given
        String accessToken = "ya29.a0AXeO80SQedEjbQXqU4RDtK_F6LEZAkfLq-C5sBotoTm8jKx5SryfWvPT3Gj_SpEVOV2IJouwJJP3eUTb0BIg587GtHWRMqAyubn0w_cy_2JN2XZ5uaiv3O1YyHYEdiP6mDXU4g2kbTuUu_GstlBb6Gcz0Cu1sPmcVcoXej5raCgYKAZoSARESFQHGX2MiRjWB_GjkDv8EMwMZ05mwiw0175";
        String token = "Bearer " + accessToken;
        String title = "test-title";
        LocalDateTime startTime = LocalDateTime.now().plusMinutes(5);

        ConferenceRequest request = new ConferenceRequest();
        request.setSummary(title);
        request.setConferenceDataVersion(1);

        // 시작 시간 설정
        ConferenceRequest.EventDateTime start = new ConferenceRequest.EventDateTime();
        start.setDateTime(startTime.toString());
        start.setTimeZone("Asia/Seoul");
        request.setStart(start);

        // 종료 시간 설정
        ConferenceRequest.EventDateTime end = new ConferenceRequest.EventDateTime();
        end.setDateTime(startTime.plusHours(1).toString());
        end.setTimeZone("Asia/Seoul");
        request.setEnd(end);


        // Google Meet 설정 추가
        ConferenceRequest.ConferenceData conferenceData = new ConferenceRequest.ConferenceData(
                new ConferenceRequest.CreateConferenceRequest(
                        UUID.randomUUID().toString(), // 유니크한 ID 생성
                        new ConferenceRequest.ConferenceSolutionKey("hangoutsMeet") // Google Meet 사용
                )
        );
        request.setConferenceData(conferenceData);

        // when
        ConferenceResponse response = googleMeetClient.createMeeting(token, request);


        System.out.println(response);
        System.out.println("생성된 링크: " + response.getHangoutLink());
    }

}
