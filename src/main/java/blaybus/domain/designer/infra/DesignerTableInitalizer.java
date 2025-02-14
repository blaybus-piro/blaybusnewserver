package blaybus.domain.designer.infra;

import blaybus.domain.designer.domain.repository.DesignerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DesignerTableInitalizer {

    private final DesignerRepository designerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public ApplicationRunner initializeDesignerTable() {
        return args -> {
            if (!isTableExists("designer")) {
                createDesignerTable();
            }
        };
    }

    private boolean isTableExists(String tableName) {
        try {
            entityManager.createNativeQuery("SELECT 1 FROM " + tableName + " LIMIT 1").getResultList();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public void createDesignerTable() {
        entityManager.createNativeQuery("""
            CREATE TABLE IF NOT EXISTS designer (
                id VARCHAR(255) PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                profile VARCHAR(50) NOT NULL,
                address VARCHAR(50) NOT NULL,
                expert_field ENUM('CUT', 'PERM', 'DYE', 'BLEACH') NOT NULL,
                introduce VARCHAR(50) NOT NULL,
                portfolio VARCHAR(50) NOT NULL,
                addressing_id INT NOT NULL,
                CONSTRAINT fk_position FOREIGN KEY (addressing_id) REFERENCES position(id)
            )
        """).executeUpdate();

    }
}