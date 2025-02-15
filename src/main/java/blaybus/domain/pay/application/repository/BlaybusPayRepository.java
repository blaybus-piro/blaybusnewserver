package blaybus.domain.pay.application.repository;

import blaybus.domain.pay.domain.BlaybusPayTid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlaybusPayRepository extends JpaRepository<BlaybusPayTid, String> {
}
