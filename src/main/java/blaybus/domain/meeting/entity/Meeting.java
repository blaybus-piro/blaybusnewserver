package blaybus.domain.meeting.entity;

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

    @Builder
    public Meeting(Long id, String title, String meetUrl, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.title = title;
        this.meetUrl = meetUrl;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
