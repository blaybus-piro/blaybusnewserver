package blaybus.domain.meeting.infra.client;

import blaybus.domain.meeting.infra.client.dto.request.ConferenceRequest;
import blaybus.domain.meeting.infra.client.dto.response.ConferenceResponse;
import blaybus.domain.meeting.infra.config.GoogleMeetFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "googleMeetClient",
        url = "https://www.googleapis.com/calendar/v3",
        configuration = GoogleMeetFeignConfig.class
)
public interface GoogleMeetClient {
    @PostMapping("/calendars/primary/events")
    ConferenceResponse createMeeting(@RequestBody ConferenceRequest request);
}
