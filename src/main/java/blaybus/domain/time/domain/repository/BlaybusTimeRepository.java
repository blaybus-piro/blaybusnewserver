package blaybus.domain.time.domain.repository;

import blaybus.domain.time.domain.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlaybusTimeRepository extends JpaRepository<Time, Long> {
    List<Time> findByUserId(String userId);


}
