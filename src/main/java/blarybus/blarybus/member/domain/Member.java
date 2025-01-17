package blarybus.blarybus.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Getter
@Setter
public class Member {

    @Id
    private String memberId;
    private String password;
    @Transient
    private String password2;

    private String loginId;

    private String name;

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
