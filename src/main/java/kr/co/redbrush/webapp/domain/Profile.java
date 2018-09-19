package kr.co.redbrush.webapp.domain;

import kr.co.redbrush.webapp.enums.CalendarType;
import kr.co.redbrush.webapp.enums.MaritalStatus;
import kr.co.redbrush.webapp.enums.Sex;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class Profile {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    private Sex sex;
    private LocalDate birthday;
    private CalendarType birthdayType;
    private MaritalStatus maritalStatus;
    private String mobileNumber;
    private String homeNumber;
    private String job;
    private String postCode;
    private String address1;
    private String address2;

    @CreatedDate
    @Column
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedDate;
}
