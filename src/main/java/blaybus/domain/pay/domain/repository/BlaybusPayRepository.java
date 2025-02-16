package blaybus.domain.pay.domain.repository;

import blaybus.domain.pay.domain.entity.BlaybusPayTid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlaybusPayRepository extends JpaRepository<BlaybusPayTid, String> {
}
