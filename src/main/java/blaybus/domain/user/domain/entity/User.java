package blaybus.domain.user.domain.entity;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.time.domain.entity.Time;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
@DynamicUpdate
public class User {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profile", nullable = false)
    private String profile;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Time> times = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulting> consultings = new ArrayList<>();
    @Builder
    public User(String id, String mail, String name, String profile, Role role) {
        this.id = id;
        this.mail = mail;
        this.name = name;
        this.profile = profile;
        this.role = role;
    }

    public void updateNameAndEmailAndProfile(String name, String email, String profile) {
        this.name = name;
        this.mail = email;
        this.profile = profile;
    }
}
