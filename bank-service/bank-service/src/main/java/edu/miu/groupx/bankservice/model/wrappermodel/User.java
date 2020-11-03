package edu.miu.groupx.bankservice.model.wrappermodel;

import edu.miu.groupx.bankservice.model.Address;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private String firstName;
    private String lastName;
    private String dob_date;
    private String dob_month;
    private String dob_year;
    private String SSN;
}
