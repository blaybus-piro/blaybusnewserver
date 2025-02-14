package blaybus.domain.consulting.domain.entity;

import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "consulting")
public class Consulting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "designer_id", nullable = false)
    private Designer designer;

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
    private int addressId;

    // meet link 없으면 null로
    @Column(name = "meet_url")
    private String meetUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private ConsultingType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "time", nullable = false, length = 20)
    private ConsultingTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ConsultingStatus status;

    public enum ConsultingType {
        ONLINE, OFFLINE, BOTH
    }

    public enum ConsultingTime {
        // 30분 단위로 쪼개야함
        // TODO
    }

    public enum ConsultingStatus {
        FREE, SCHEDULED, CANCELED, COMPLETE
    }

}