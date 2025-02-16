package blaybus.domain.time.domain.entity;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "time")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "consulting_id", nullable = false)
    private Consulting consulting;

    @ManyToOne
    @JoinColumn(name = "designer_id", nullable = false)
    private Designer designer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    LocalDateTime start;


}