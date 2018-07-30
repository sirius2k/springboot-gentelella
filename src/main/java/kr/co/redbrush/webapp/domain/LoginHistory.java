package kr.co.redbrush.webapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class LoginHistory {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private Date loginDate;

    @ManyToOne
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "FK_account_login_history"))
    private Account account;
}
