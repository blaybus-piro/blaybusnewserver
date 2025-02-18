package blaybus.domain.consulting.domain.entity;

import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.meeting.domain.entity.Meeting;
import blaybus.domain.user.domain.entity.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "designer_id", nullable = false)
    @JsonIgnore
    private Designer designer;

    @OneToOne
    @JoinColumn(name = "meet_id")
    @JsonIgnore
    private Meeting meeting;

    @JoinColumn(name = "start_time")
    private LocalDateTime startTime;

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
    public static Consulting createConsulting(User user, Designer designer, Meeting meeting, String pay, ConsultingType type, ConsultingStatus status, LocalDateTime startTime) {
        return Consulting.builder()
                .user(user)
                .designer(designer)
                .meeting(meeting)
                .type(type)
                .status(status)
                .pay(pay)
                .startTime(startTime) // 추가
                .build();
    }
}
