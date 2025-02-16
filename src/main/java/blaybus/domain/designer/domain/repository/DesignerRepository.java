package blaybus.domain.designer.domain.repository;

import blaybus.domain.designer.domain.entity.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import blaybus.domain.consulting.domain.entity.Consulting.ConsultingType;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesignerRepository extends JpaRepository<Designer, String> {

    List<Designer> findByArea(Designer.Area area);

    @Query("""
        SELECT d FROM Designer d
        WHERE :consultingType = 'ONLINE' AND 'ONLINE' MEMBER OF d.types 
        ORDER BY 
            CASE WHEN :sortOrder = 'ASC' THEN d.onlinePrice END ASC,
            CASE WHEN :sortOrder = 'DESC' THEN d.onlinePrice END DESC
    """)
    List<Designer> findOnlineConsultingDesigners(@Param("consultingType") Type consultingType,
                                                 @Param("sortOrder") String sortOrder);

    @Query("""
        SELECT d FROM Designer d 
        WHERE :consultingType = 'OFFLINE' AND 'OFFLINE' MEMBER OF d.types 
        ORDER BY 
            CASE WHEN :sortOrder = 'ASC' THEN d.offlinePrice END ASC,
            CASE WHEN :sortOrder = 'DESC' THEN d.offlinePrice END DESC
    """)
    List<Designer> findOfflineConsultingDesigners(@Param("consultingType") ConsultingType consultingType,
                                                  @Param("sortOrder") String sortOrder);
}