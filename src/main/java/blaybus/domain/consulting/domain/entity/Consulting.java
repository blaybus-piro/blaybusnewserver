package blaybus.domain.consulting.domain.entity;

import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.meeting.domain.entity.Meeting;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "designer_id", nullable = false)
    private Designer designer;

    @OneToOne
    @JoinColumn(name = "meet_id")
    private Meeting meeting;

    @JoinColumn(name = "start_time")
    private String startTime;

    // 결제 방식
    @Column(name = "pay", nullable = false, length = 5)
    private String pay;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private ConsultingType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ConsultingStatus status;

    // 🔹 상태 변경 메서드
    public void updateStatus(ConsultingStatus newStatus) {
        this.status = newStatus;
    }

    // 빌더 패턴을 활용한 생성 메서드
    public static Consulting createConsulting(User user, Designer designer, Meeting meeting, ConsultingType type, ConsultingStatus status) {
        return Consulting.builder()
                .user(user)
                .designer(designer)
                .meeting(meeting)
                .type(type)
                .status(status)
                .build();
    }
}
