package blaybus.domain.metting.infra.feignclient;

import blaybus.domain.metting.presentation.dto.google.request.GoogleEventRequest;
import blaybus.domain.metting.presentation.dto.google.response.GoogleEventResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "googleCalendarClient",
        url = "https://www.googleapis.com"
)
public interface BlayBusMeetFeignClient {
    @PostMapping("/calendar/v3/calendars/{calendarId}/events?conferenceDataVersion=1")
    GoogleEventResponse createEvent(
            @PathVariable("calendarId") String calendarId,
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody GoogleEventRequest requestBody
    );
}

