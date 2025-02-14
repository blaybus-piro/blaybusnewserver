package blaybus.domain.designer.domain.repository;

import blaybus.domain.designer.domain.entity.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepository extends JpaRepository<Designer, String> {
}