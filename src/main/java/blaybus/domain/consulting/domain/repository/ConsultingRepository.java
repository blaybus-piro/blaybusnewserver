package blaybus.domain.consulting.domain.repository;

import blaybus.domain.consulting.domain.entity.Consulting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultingRepository extends JpaRepository<Consulting, Long> {

    // 특정 유저의 상담 목록 조회
    List<Consulting> findByUserId(String userId);

    // 특정 디자이너의 상담 목록 조회
    List<Consulting> findByDesignerId(String designerId);

    // 특정 상태의 상담 목록 조회
    List<Consulting> findByStatus(Consulting.ConsultingStatus status);
}
