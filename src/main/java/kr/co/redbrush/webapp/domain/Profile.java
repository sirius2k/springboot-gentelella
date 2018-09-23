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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id",  nullable = false, foreignKey = @ForeignKey(name = "FK_profile_account"))
    private Account account;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "profile")
    private ProfileImage profileImage;
}
