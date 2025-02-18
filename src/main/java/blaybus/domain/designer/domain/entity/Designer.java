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

    @Column(nullable = false, length = 50)
    private String profile;

    @Column(nullable = false, length = 30)
    private String area;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ExpertField expertField;

    @Column(nullable = false, length = 100)
    private String introduce;

    // Position 테이블의 name을 참조하는 FK 설정
    @ManyToOne
    @JoinColumn(name = "position_name", referencedColumnName = "name", nullable = false)
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
}
