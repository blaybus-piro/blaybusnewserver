package blaybus.domain.map.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Geometry(
        @JsonProperty("location")
        Location location
) {
}
