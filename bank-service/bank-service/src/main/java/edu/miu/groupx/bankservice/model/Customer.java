package edu.miu.groupx.bankservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String dob_date;
    private String dob_month;
    private String dob_year;
    private String SSN;
    @JsonIgnore
    @OneToOne(mappedBy = "accountHolder", cascade = CascadeType.ALL)
    private CheckingAccount account;
    @OneToOne
    private  Address address;





}
