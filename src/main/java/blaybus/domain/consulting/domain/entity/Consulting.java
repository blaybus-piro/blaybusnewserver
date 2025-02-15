package blaybus.domain.consulting.domain.entity;

import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.meeting.entity.Meeting;
import blaybus.domain.position.domain.entity.Position;
import blaybus.domain.user.domain.entity.User;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "consulting")
public class Consulting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    // Meeting 테이블을 참조하는 FK (nullable 가능)
    @ManyToOne
    @JoinColumn(name = "meet_id")
    private Meeting meeting;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private ConsultingType type;

    /*

    // time 관련은 일단 유기
    @Column(name = "time", nullable = false)
    private LocalTime time;  // 30분 단위 시간 저장

    */

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ConsultingStatus status;

    public enum ConsultingType {
        ONLINE, OFFLINE
    }

    public enum ConsultingStatus {
        FREE, SCHEDULED, CANCELED, COMPLETE
    }

    public String getMeetUrl() {
        return (meeting != null) ? meeting.getMeetUrl() : null;
    }
}
