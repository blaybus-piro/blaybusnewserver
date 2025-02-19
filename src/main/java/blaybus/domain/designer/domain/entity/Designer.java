package blaybus.domain.designer.domain.entity;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.map.domain.entity.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(columnDefinition = "TEXT")
    private String profile;


    @Column(nullable = false, length = 30)
    private String area;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ExpertField expertField;

    @Column(nullable = false, length = 100)
    private String introduce;

    // 양방향 관계 설정 (Position 테이블과 다대일)
    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    @JsonIgnore
    private Position position;

    @ElementCollection
    @CollectionTable(name = "designer_portfolios", joinColumns = @JoinColumn(name = "designer_id"))
    @Column(name = "portfolio_url")
    private List<String> portfolios;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 10)
    private Type type;

    @Column(nullable = false)
    private int offlinePrice;

    @Column(nullable = false)
    private int onlinePrice;

    @OneToMany(mappedBy = "designer")
    @JsonIgnore
    private List<Consulting> consultings = new ArrayList<>();
    // ✅ 명시적인 생성자 추가 (area 필드 포함)
    @Builder
    public Designer(String name, String profile, String area, Position position,
                    ExpertField expertField, String introduce, List<String> portfolios, Type type,
                    int offlinePrice, int onlinePrice) {
        this.name = name;
        this.profile = profile;
        this.area = area;
        this.position = position;
        this.expertField = expertField;
        this.introduce = introduce;
        this.portfolios = portfolios;
        this.type = type;
        this.offlinePrice = offlinePrice;
        this.onlinePrice = onlinePrice;
    }

}

