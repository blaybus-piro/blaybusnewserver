//package blaybus.domain.time.domain.entity;
//
//import blaybus.domain.consulting.domain.entity.Consulting;
//import blaybus.domain.designer.domain.entity.Designer;
//import blaybus.domain.user.domain.entity.User;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Table(name = "time")
//public class Time {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "consulting_id", nullable = false)
//    private Consulting consulting;
//
//    @ManyToOne
//    @JoinColumn(name = "designer_id", nullable = false)
//    private Designer designer;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    // 예약 날짜 및 시간
//    private LocalDateTime startTime;
//
//    // 🔹 빌더 메서드 추가
//    public static Time createTime(Consulting consulting, Designer designer, User user, LocalDateTime startTime) {
//        return Time.builder()
//                .consulting(consulting)
//                .designer(designer)
//                .user(user)
//                .startTime(startTime)
//                .build();
//    }
//}
