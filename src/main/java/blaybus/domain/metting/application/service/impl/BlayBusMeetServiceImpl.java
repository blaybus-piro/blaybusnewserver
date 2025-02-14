package blaybus.domain.metting.application.service.impl;

import blaybus.domain.metting.application.service.BlayBusMeetService;
import blaybus.domain.metting.application.service.GoogleOAuthService;
import blaybus.domain.metting.infra.feignclient.BlayBusMeetFeignClient;
import blaybus.domain.metting.presentation.dto.CreateMeetRequest;
import blaybus.domain.metting.presentation.dto.CreateMeetResponse;
import blaybus.domain.metting.presentation.dto.google.request.*;
import blaybus.domain.metting.presentation.dto.google.response.GoogleEventResponse;
import blaybus.domain.metting.presentation.dto.google.response.EntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
// 기타 import 생략

@Service
@RequiredArgsConstructor
@Slf4j
public class BlayBusMeetServiceImpl {

    private final BlayBusMeetFeignClient blayBusMeetFeignClient;
    private final GoogleOAuthService googleOAuthService;

    // 캘린더 ID (예: "primary")
    private static final String CALENDAR_ID = "primary";

    public CreateMeetResponse createMeet(CreateMeetRequest request) {
        try {
            log.info("Starting createMeet");

            // 1) OAuth 토큰 가져오기
            String accessToken = googleOAuthService.getAccessToken();
            log.info(accessToken);

            // 2) GoogleEventRequest 생성
            GoogleEventRequest eventRequest = buildEventRequest(request);

            // ** JSON 직렬화하여 로그로 출력 **
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(eventRequest);
                log.info("Serialized GoogleEventRequest JSON: {}", json);
            } catch (Exception e) {
                log.error("Error serializing GoogleEventRequest: {}", e.getMessage());
            }

            // 3) FeignClient로 이벤트 생성 API 호출
            GoogleEventResponse googleEventResponse = blayBusMeetFeignClient.createEvent(
                    CALENDAR_ID,
                    "Bearer " + accessToken,
                    eventRequest
            );
            log.info("Full GoogleEventResponse: {}", googleEventResponse);

            // 4) Meet 링크 추출
            String meetLink = extractMeetLink(googleEventResponse);
            log.info("Extracted Meet Link: {}", meetLink);

            // 5) CreateMeetResponse 반환
            return new CreateMeetResponse(meetLink);

        } catch (IOException e) {
            throw new RuntimeException("Failed to get Google OAuth token", e);
        }
    }

    /**
     * CreateMeetRequest로부터 GoogleEventRequest를 구성하는 메서드
     */
    private GoogleEventRequest buildEventRequest(CreateMeetRequest request) {
        // 예시: 현재 시간부터 30분 후까지의 이벤트
        ZonedDateTime startTime = ZonedDateTime.now();
        ZonedDateTime endTime = startTime.plusMinutes(30);

        EventDateTime start = new EventDateTime(
                startTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                "Asia/Seoul"
        );
        EventDateTime end = new EventDateTime(
                endTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                "Asia/Seoul"
        );

        // 예시: 회의 생성 요청
        CreateConferenceRequest createConferenceRequest = new CreateConferenceRequest(
                UUID.randomUUID().toString(),
                new ConferenceSolutionKey("hangoutsMeet")
        );
        ConferenceData conferenceData = new ConferenceData(createConferenceRequest);

        GoogleEventRequest eventRequest = new GoogleEventRequest();
        eventRequest.setSummary("미팅: " + request.getTitle());
        eventRequest.setDescription("설명: " + request.getDesc());
        eventRequest.setStart(start);
        eventRequest.setEnd(end);
        eventRequest.setConferenceData(conferenceData);

        return eventRequest;
    }

    /**
     * GoogleEventResponse에서 Meet 링크 추출
     */
    private String extractMeetLink(GoogleEventResponse response) {
        // 1) hangoutLink가 있는 경우
        if (StringUtils.hasText(response.getHangoutLink())) {
            return response.getHangoutLink();
        }
        // 2) conferenceData.entryPoints에서 "video" 타입의 URI 추출
        if (response.getConferenceData() != null
                && response.getConferenceData().getEntryPoints() != null) {
            return response.getConferenceData().getEntryPoints().stream()
                    .filter(ep -> "video".equals(ep.getEntryPointType()))
                    .map(EntryPoint::getUri)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}
