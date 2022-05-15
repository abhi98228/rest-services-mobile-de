package de.mobile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mobile_customer_DB")
public class MobileCustomer {
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String companyName;

    private String email;
}
