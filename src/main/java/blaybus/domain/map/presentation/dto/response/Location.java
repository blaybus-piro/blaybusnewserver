package blaybus.domain.map.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Location(
        @JsonProperty("lat")
        double lat,

        @JsonProperty("lng")
        double lng
) {
}
