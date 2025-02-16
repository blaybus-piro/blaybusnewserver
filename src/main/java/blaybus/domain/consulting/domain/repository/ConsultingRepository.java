package blaybus.domain.consulting.domain.repository;

import blaybus.domain.consulting.domain.entity.Consulting;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultingRepository extends JpaRepository<Consulting, String> {

    // 특정 유저의 상담 목록 조회
    List<Consulting> findByUserId(String userId);

    Optional<Object> findById(@NotNull long id);
}
