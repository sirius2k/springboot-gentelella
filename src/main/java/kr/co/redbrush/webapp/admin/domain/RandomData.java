package kr.co.redbrush.webapp.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RandomData {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer value;
    private Long sum;
    private Date createdDate;
}
