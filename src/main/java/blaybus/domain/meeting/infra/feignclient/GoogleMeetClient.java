package blaybus.domain.meeting.infra.feignclient;

import blaybus.domain.meeting.infra.feignclient.dto.request.ConferenceRequest;
import blaybus.domain.meeting.infra.feignclient.dto.response.ConferenceResponse;
import blaybus.domain.meeting.infra.config.GoogleMeetFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "googleMeetClient",
        url = "https://www.googleapis.com/calendar/v3",
        configuration = GoogleMeetFeignConfig.class
)
public interface GoogleMeetClient {
    @PostMapping("/calendars/primary/events?conferenceDataVersion=1")
    ConferenceResponse createMeeting(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody ConferenceRequest request);
}
