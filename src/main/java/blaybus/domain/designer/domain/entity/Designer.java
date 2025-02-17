package blaybus.domain.designer.domain.entity;

import blaybus.domain.map.domain.entity.Position;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "designer")
public class Designer {

    @Id
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Area area;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ExpertField expertField;

    @Column(nullable = false, length = 100)
    private String introduce;

    // Position 테이블의 name을 참조하는 FK 설정
    @ManyToOne
    @JoinColumn(name = "position_name", referencedColumnName = "name", nullable = false)
    private Position position;

    @Column(nullable = false, length = 50)
    private String portfolio;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 10)
    private Type type;

    @Column(nullable = false)
    private int offlinePrice;

    @Column(nullable = false)
    private int onlinePrice;
}
