package blarybus.blarybus.board.domain;

import blarybus.blarybus.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Slf4j
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    @CreatedDate
    private LocalDate createdDate;

    @Builder
    public Board(Long boardId, Member member, String title, String content, LocalDate createdDate) {
        this.boardId = boardId;
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    public Board(){

    }
}
