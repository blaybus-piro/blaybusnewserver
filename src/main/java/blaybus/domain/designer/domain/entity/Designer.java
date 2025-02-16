package blaybus.domain.designer.domain.entity;

import blaybus.domain.designer.domain.entity.ExpertField;
import blaybus.domain.designer.domain.entity.Type;
import blaybus.domain.designer.domain.entity.Area;
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

    /*
    // Position 테이블을 참조하는 FK
    // 필요시 DTO에서 addressId를 생성할때 getId() 사용하도록 생성
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Position position;
    */

    @Column(nullable = false, length = 50)
    private String portfolio;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 10)
    private Type type; // EnumSet 대신 단일 Enum 필드로 변경

    @Column(nullable = false)
    private int offlinePrice;

    @Column(nullable = false)
    private int onlinePrice;
}