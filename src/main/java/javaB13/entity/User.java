package javaB13.entity;

import jakarta.persistence.*;
import javaB13.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_gen", allocationSize = 1, initialValue = 6)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String resetPasswordToken;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserInfo userInfo;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @PrePersist
    protected void onCreate() {
        modifiedAt = LocalDateTime.now();
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedAt = LocalDateTime.now();
    }
}
