package blaybus.domain.metting.presentation.dto.google.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceData {
    // JSON 필드명은 "createRequest"여야 합니다.
    @JsonProperty("createRequest")
    private CreateConferenceRequest createRequest;
}