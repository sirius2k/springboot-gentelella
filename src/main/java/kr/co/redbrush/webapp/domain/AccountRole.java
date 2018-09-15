package kr.co.redbrush.webapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "roleName")
@Entity
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long arid;

    @Column(nullable = false, unique = true)
    private String roleName;

    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    @Column
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedDate;
}
