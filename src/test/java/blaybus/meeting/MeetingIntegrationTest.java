package blaybus.meeting;

import blaybus.domain.meeting.application.service.impl.MeetingServiceImpl;
import blaybus.domain.meeting.infra.feignclient.GoogleMeetClient;
import blaybus.domain.meeting.infra.feignclient.dto.request.ConferenceRequest;
import blaybus.domain.meeting.infra.feignclient.dto.response.ConferenceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 실제 서버 포트로 테스트
@Transactional
public class MeetingIntegrationTest {

    @Autowired
    private GoogleMeetClient googleMeetClient; // FeignClient 주입

    @Autowired
    private MeetingServiceImpl meetingServiceImpl; // 실제 서비스 호출

    @Test
    void Google_Meet_생성_테스트() {
        // given
        String accessToken = "ya29.a0AXeO80ROYE5FrVjnulkzIPpJ7DXlZHmahsm-fh16MhD0hAwfo7PwWVdISKPsjTWdYj3UQHwSwqw2feDHseC3cfSj7LJ6lu6DdhaBWkr4RZoLCY1LzWDPikWnitixIXPkIm99RFLBRlwtixAGuPOWi331evPx8k9j_SoJhiiwaCgYKASMSARESFQHGX2MiQFd7NyoyzvyC6CSz4G3V_w0175";
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

    @Test
    void 미팅_이름에_디자이너_이름_포함_생성_테스트() {
        // given
        String accessToken = "ya29.a0AXeO80ROYE5FrVjnulkzIPpJ7DXlZHmahsm-fh16MhD0hAwfo7PwWVdISKPsjTWdYj3UQHwSwqw2feDHseC3cfSj7LJ6lu6DdhaBWkr4RZoLCY1LzWDPikWnitixIXPkIm99RFLBRlwtixAGuPOWi331evPx8k9j_SoJhiiwaCgYKASMSARESFQHGX2MiQFd7NyoyzvyC6CSz4G3V_w0175";
        String token = "Bearer " + accessToken;
        String designerName = "이초 디자이너";
        LocalDateTime startTime = LocalDateTime.now().plusMinutes(5);

        String title = meetingServiceImpl.generateMeetingTitle(designerName, startTime);

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
