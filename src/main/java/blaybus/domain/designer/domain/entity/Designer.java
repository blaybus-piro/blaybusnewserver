package blaybus.domain.designer.domain.entity;

import blaybus.domain.position.domain.entity.Position;
import jakarta.persistence.*;
import lombok.*;

import java.util.EnumSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "designer")

public class Designer {

    @Id
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Area area;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ExpertField expertField;

    @Column(nullable = false, length = 50)
    private String introduce;

    // Position 테이블을 참조하는 FK
    // 필요시 DTO에서 addressId를 생성할때 getId() 사용하도록 생성
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Position position;

    @Column(nullable = false, length = 50)
    private String portfolio;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "designer_types", joinColumns = @JoinColumn(name = "designer_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Set<Type> types = EnumSet.noneOf(Type.class); // 기본값을 EnumSet으로 설정

    @Column(nullable = false)
    private int offlinePrice;

    @Column(nullable = false)
    private int onlinePrice;

    public enum ExpertField {
        CUT, PERM, DYE, BLEACH
    }

    public enum Area {
        SEOUL_ALL, GANGNAM_CHEONGDAM_APGUJEONG, SEONGSU_KONDAE, HONGDAE_YEONNAM_HAPJEONG
    }

    public enum Type {
        ONLINE, OFFLINE
    }

    public void addType(Type type) {
        this.types.add(type);
    }

    public void removeType(Type type) {
        this.types.remove(type);
    }
}
