package blaybus.domain.oauth2.presentation.dto.response;

public interface OAuth2Response {
    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();

    String getProfile();
}
