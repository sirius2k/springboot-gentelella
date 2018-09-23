package kr.co.redbrush.webapp.domain;

import kr.co.redbrush.webapp.enums.CalendarType;
import kr.co.redbrush.webapp.enums.MaritalStatus;
import kr.co.redbrush.webapp.enums.Sex;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class ProfileImage {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;
    private String fileType;
    private long size;
    private long width;
    private long height;

    @CreatedDate
    @Column
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", nullable = false, foreignKey = @ForeignKey(name = "FK_profile_image_profile"))
    private Profile profile;
}
