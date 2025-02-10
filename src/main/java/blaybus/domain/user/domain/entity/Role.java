package blaybus.domain.user.domain.entity;

import blaybus.domain.user.domain.exception.InvalidRoleException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ANONYMOUS("ROLE_ANONYMOUS"),
    USER("ROLE_USER"),
    DESIGNER("ROLE_DESIGNER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    public static Role getByValue(String value) {
        for (Role role : Role.values()) {
            if (role.value.equals(value)) {
                return role;
            }
        }
        throw new InvalidRoleException(value + "라는 역할이 존재하지 않습니다.");
    }
}
