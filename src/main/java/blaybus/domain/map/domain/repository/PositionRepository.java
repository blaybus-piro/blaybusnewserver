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
        SELECT *,
            (6371 * 2 * ASIN(
                SQRT(
                    POWER(SIN(RADIANS(:latitude - l.lat) / 2), 2) +
                    COS(RADIANS(:latitude)) * COS(RADIANS(l.lat)) *
                    POWER(SIN(RADIANS(:longitude - l.lon) / 2), 2)
                )
            )) AS distance
        FROM position p
        ORDER BY distance ASC
        """, nativeQuery = true)
    List<Position> findAllOrderByDistance(@Param("latitude") double latitude,
                                          @Param("longitude") double longitude);
}
