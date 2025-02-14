package blaybus.domain.oauth2.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OAuth2UserResponse(
        @JsonProperty("sub")
        String id,

        @JsonProperty("name")
        String name,

        @JsonProperty("email")
        String email,

        @JsonProperty("picture")
        String profile
) {
}
