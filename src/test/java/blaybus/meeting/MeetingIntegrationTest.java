package blaybus.meeting;

import blaybus.domain.meeting.application.service.impl.MeetingServiceImpl;
import blaybus.domain.meeting.infra.feignclient.GoogleMeetClient;
import blaybus.domain.meeting.infra.feignclient.dto.request.*;
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
        String accessToken = "ya29.a0AXeO80S7YXJKdsQw74uwM036cJJEbWOf29mb2ehXgB_n7SiE0q7SY_WapfbbUyRbfesD0429AL2NROSi9-2fWwK9MvQtAU-P0ft923CllKzHJtTAoY12HSz0f9vxuJGxKVCfgStqgRvY2b0eKtddOb255e0xl1Qagmu3FWmzaCgYKAYsSARESFQHGX2MihuoO6CJIV66st8YiyhJhCA0175";
        String token = "Bearer " + accessToken;
        String title = "test-title";
        LocalDateTime startTime = LocalDateTime.now().plusMinutes(5);
        LocalDateTime endTime = startTime.plusHours(1);

        // Create EventDateTime objects
        EventDateTime start = new EventDateTime(
                startTime.toString(),
                "Asia/Seoul"
        );

        EventDateTime end = new EventDateTime(
                endTime.toString(),
                "Asia/Seoul"
        );

        // Create ConferenceData
        ConferenceSolutionKey solutionKey = new ConferenceSolutionKey("hangoutsMeet");
        CreateConferenceRequest createRequest = new CreateConferenceRequest(
                UUID.randomUUID().toString(),
                solutionKey
        );
        ConferenceData conferenceData = new ConferenceData(createRequest);

        // Create final ConferenceRequest
        ConferenceRequest request = new ConferenceRequest(
                title,
                start,
                end,
                conferenceData,
                1
        );

        // when
        ConferenceResponse response = googleMeetClient.createMeeting(token, request);

        // then
        System.out.println(response);
        System.out.println("생성된 링크: " + response.hangoutLink());
    }

    @Test
    void 미팅_이름에_디자이너_이름_포함_생성_테스트() {
        // given
        String accessToken = "ya29.a0AXeO80S7YXJKdsQw74uwM036cJJEbWOf29mb2ehXgB_n7SiE0q7SY_WapfbbUyRbfesD0429AL2NROSi9-2fWwK9MvQtAU-P0ft923CllKzHJtTAoY12HSz0f9vxuJGxKVCfgStqgRvY2b0eKtddOb255e0xl1Qagmu3FWmzaCgYKAYsSARESFQHGX2MihuoO6CJIV66st8YiyhJhCA0175";
        String token = "Bearer " + accessToken;
        String designerName = "이초 디자이너";
        LocalDateTime startTime = LocalDateTime.now().plusMinutes(5);
        LocalDateTime endTime = startTime.plusHours(1);

        String title = meetingServiceImpl.generateMeetingTitle(designerName, startTime);

        // Create EventDateTime objects
        EventDateTime start = new EventDateTime(
                startTime.toString(),
                "Asia/Seoul"
        );

        EventDateTime end = new EventDateTime(
                endTime.toString(),
                "Asia/Seoul"
        );

        // Create ConferenceData
        ConferenceSolutionKey solutionKey = new ConferenceSolutionKey("hangoutsMeet");
        CreateConferenceRequest createRequest = new CreateConferenceRequest(
                UUID.randomUUID().toString(),
                solutionKey
        );
        ConferenceData conferenceData = new ConferenceData(createRequest);

        // Create final ConferenceRequest
        ConferenceRequest request = new ConferenceRequest(
                title,
                start,
                end,
                conferenceData,
                1
        );

        // when
        ConferenceResponse response = googleMeetClient.createMeeting(token, request);


        System.out.println(response);
        System.out.println("생성된 링크: " + response.hangoutLink());
    }



}
