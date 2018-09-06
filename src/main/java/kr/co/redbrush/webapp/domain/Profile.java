package kr.co.redbrush.webapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class Profile {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @CreatedDate
    @Column
    private Date createdDate;

    @LastModifiedDate
    @Column
    private Date updatedDate;
}
