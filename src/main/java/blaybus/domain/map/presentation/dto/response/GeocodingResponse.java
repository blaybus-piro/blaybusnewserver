package blaybus.domain.map.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GeocodingResponse (
        @JsonProperty("results")
        List<Result> results
) {}
