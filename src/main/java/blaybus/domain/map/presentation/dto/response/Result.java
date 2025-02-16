package blaybus.domain.map.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Result(
        @JsonProperty("geometry")
        Geometry geometry
) {
}
