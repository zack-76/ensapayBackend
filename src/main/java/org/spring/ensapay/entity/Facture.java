package org.spring.ensapay.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Facture {


    @Id
    private Integer reference ;
    @Column(nullable = false)
    private String clientName;
    @Column(nullable = false)
    private String creditorName;
    @Column(nullable = false)
    private String DebtName;
    @Column(nullable = false)
    private Integer impaye;
    @Column(nullable = false)
    private String numeroClient;
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creationDate;


}
