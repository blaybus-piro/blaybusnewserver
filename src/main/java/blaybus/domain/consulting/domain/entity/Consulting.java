package blaybus.domain.consulting.domain.entity;

import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.meeting.entity.Meeting;
import blaybus.domain.map.domain.entity.Position;
import blaybus.domain.user.domain.entity.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalTime;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consulting")
public class Consulting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "designer_id", nullable = false)
    private Designer designer;

    /*
    // Position 테이블을 참조하는 FK
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Position position;
    */

    // Meeting 테이블을 참조하는 FK (nullable 가능)
    @ManyToOne
    @JoinColumn(name = "meet_id")
    private Meeting meeting;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private ConsultingType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ConsultingStatus status;

    /*

    // time 관련은 일단 유기
    @Column(name = "time", nullable = false)
    private LocalTime time;  // 30분 단위 시간 저장

    */

    /*

    public String getMeetUrl() {
        return (meeting != null) ? meeting.getMeetUrl() : null;
    }

    */

    @Builder
    public Consulting(User user, Designer designer, Meeting meeting, ConsultingType type, ConsultingStatus status) {
        this.user = user;
        this.designer = designer;
        this.meeting = meeting;
        this.type = type;
        this.status = status;
    }

    // 🔹 상태 변경을 위한 메서드 추가
    public void updateStatus(ConsultingStatus newStatus) {
        this.status = newStatus;
    }
}
