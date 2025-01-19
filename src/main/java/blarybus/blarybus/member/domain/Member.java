package blarybus.blarybus.member.domain;

import blarybus.blarybus.board.domain.Board;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Entity
@Slf4j
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {

    @Id
    private String memberId;
    private String password;
    @Transient
    private String password2;

    private String loginId;

    private String name;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Board> board = new ArrayList<>();
    @Builder
    public Member(String memberId, String password, String loginId, String name) {
        this.memberId = memberId;
        this.password = password;
        this.loginId = loginId;
        this.name=name;
    }

    public Member() {

    }
}
