package blaybus.domain.position.presentation;

import blaybus.domain.position.domain.entity.Position;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionDTO {

    private int id;
    private float latitude;
    private float longitude;
    private String name;

    public static PositionDTO fromEntity(Position position) {
        return PositionDTO.builder()
                .id(position.getId())
                .latitude(position.getLatitude())
                .longitude(position.getLongitude())
                .name(position.getName())
                .build();
    }
}