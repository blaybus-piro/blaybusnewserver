package blaybus.domain.designer.domain.repository;

import blaybus.domain.designer.domain.entity.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignerRepository extends JpaRepository<Designer, String> {

    @Query("""
        SELECT d FROM Designer d
        WHERE d.type IN ('ONLINE', 'BOTH')
        ORDER BY
            CASE WHEN :sortOrder = 'ASC' THEN d.onlinePrice END ASC,
            CASE WHEN :sortOrder = 'DESC' THEN d.onlinePrice END DESC
    """)
    List<Designer> findOnlineConsultingDesigners(@Param("sortOrder") String sortOrder);

    @Query("""
        SELECT d FROM Designer d
        WHERE d.type IN ('OFFLINE', 'BOTH')
        ORDER BY
            CASE WHEN :sortOrder = 'ASC' THEN d.offlinePrice END ASC,
            CASE WHEN :sortOrder = 'DESC' THEN d.offlinePrice END DESC
    """)
    List<Designer> findOfflineConsultingDesigners(@Param("sortOrder") String sortOrder);

//    @Query("""
//    SELECT d FROM Designer d
//    WHERE d.position.name IN :names
//    AND (d.type = :type OR d.type = 'BOTH')
//    ORDER BY FIELD(d.position.name, :names), d.name ASC
//""")
//    List<Designer> findAllByPositionNameAndTypeInOrderByCustomOrder(
//            @Param("names") List<PositionResponseDTO> names,
//            @Param("type") Type type);

}
