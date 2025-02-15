package blaybus.domain.position.domain.repository;


import blaybus.domain.position.domain.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    
}