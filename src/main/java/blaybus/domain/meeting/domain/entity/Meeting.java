package blaybus.domain.meeting.domain.entity;

import blaybus.domain.consulting.domain.entity.Consulting;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String meetUrl;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToOne(mappedBy = "meeting")
    private Consulting consulting;

    @Builder
    public Meeting(String title, String meetUrl, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.meetUrl = meetUrl;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
