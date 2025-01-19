package blarybus.blarybus.member.repository;

import blarybus.blarybus.member.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberRepository {

    private final EntityManager em;


    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> existLoginId(String loginId) {
        try {
            Member findMember = em.createQuery("select m from Member m where m.loginId = :id ", Member.class)
                    .setParameter("id", loginId)
                    .getSingleResult();
            return Optional.of(findMember);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Member findMemberId(String memberId) {
        Member member = em.find(Member.class, memberId);
        return member;
    }

}
