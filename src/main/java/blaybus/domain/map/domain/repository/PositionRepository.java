package blaybus.domain.map.domain.repository;

import blaybus.domain.map.domain.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    @Query(value = """
    SELECT sub.name, sub.distance
    FROM (
        SELECT p.name,\s
               (6371 * 2 * ASIN(
                    SQRT(
                        POWER(SIN(RADIANS(:latitude - p.latitude) / 2), 2) +
                        COS(RADIANS(:latitude)) * COS(RADIANS(p.latitude)) *
                        POWER(SIN(RADIANS(:longitude - p.longitude) / 2), 2)
                    )
                )) AS distance
        FROM position p
    ) sub
    ORDER BY sub.distance ASC;
    """, nativeQuery = true)
    List<Object[]> findAllOrderByDistance(@Param("latitude") double latitude,
                                          @Param("longitude") double longitude);
}
