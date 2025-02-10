package blaybus.domain.user.presentation;

public record UserDTO(
        String id,
        String name,
        String email,
        String profile
) {
}
