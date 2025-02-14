package blaybus.domain.metting.presentation.dto.google.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateConferenceRequest {
    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("conferenceSolutionKey")
    private ConferenceSolutionKey conferenceSolutionKey;
}