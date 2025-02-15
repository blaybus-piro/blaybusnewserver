package blaybus.domain.position.presentation;

import blaybus.domain.position.domain.entity.Position;

public record PositionDTO(
        int id,
        float latitude,
        float longitude,
        String name
)
{
    public static PositionDTO fromEntity(Position position) {
        return new PositionDTO(
                position.getId(),
                position.getLatitude(),
                position.getLongitude(),
                position.getName()
        );
    }
}

