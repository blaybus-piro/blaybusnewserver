package blaybus.domain.designer.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
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

    @Column(nullable = false, length = 50)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ExpertField expertField;

    @Column(nullable = false, length = 50)
    private String introduce;

    // Position 테이블을 참조하는 FK
    // position의 id에 맞게 저장하고 필요시에 id를 정수로 탐색하게 하는 편이 빠를 거 같아서 이렇게 진행
    // 이후 필요하면 Join 형태로 변경

    /*
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Position position;
    */

    // 그런데 왜 consulting에 addressId가 필요하지?

    @Column(name = "address_id", nullable = false)
    private int addressId; // Position 테이블의 ID를 참조하는 FK

    @Column(nullable = false, length = 50)
    private String portfolio;

    public enum ExpertField {
        CUT, PERM, DYE, BLEACH
    }
}