package blaybus.domain.position.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "position")
public class Position {

    @Id
    private int id; // PK, Consulting과 Designer에서 참조됨

    @Column(nullable = false)
    private float latitude; // 위도

    @Column(nullable = false)
    private float longitude; // 경도

    @Column(nullable = false, length = 100)
    private String name; // 위치명
}