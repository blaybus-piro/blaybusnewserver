package blaybus.domain.consulting.domain.entity;

import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.position.domain.entity.Position;
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
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Position position;


    @Column(name = "address_id", nullable = false)
    private int addressId;

    // meet link 없으면 null로
    // meet id에 없으면 null 있으면 fk
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
        ONLINE, OFFLINE
    }

    public enum ConsultingTime {
        // 30분 단위로 쪼개야함
        // TODO
    }

    public enum ConsultingStatus {
        FREE, SCHEDULED, CANCELED, COMPLETE
    }

}