package org.spring.ensapay.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agentId;
    @Column(nullable = false, length = 25)
    private String agentPhone;
    @Column(nullable = false, length = 20)

    private String agentFullName;

    @Column(nullable = false)
    private String agentAddress;
    @Column(nullable = false, length = 20, name = "agent_cin")
    private String agentCIN;
    @Column(nullable = false, length = 100)
    private String agentEmail;
    @Column(nullable = false, length = 100)
    private String agentCity;
    @Column(nullable = false, length = 100)
    private String agentZip;
    @Column(nullable = false, length = 100)
    private String agentCountry;
    private Long idbackOffice;
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creationDate;

    private boolean firstConnection;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    User agentUser;

}
