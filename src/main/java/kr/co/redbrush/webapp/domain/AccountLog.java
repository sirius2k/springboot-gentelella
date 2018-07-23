package kr.co.redbrush.webapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class AccountLog {
    @Id
    @GeneratedValue
    private long alid;

    @Column
    private Date loginTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Account account;
}
