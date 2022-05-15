package de.mobile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mobile_ad_DB")
public class MobileAd {
    @Id
    private Long customerId;

    private String make;

    private String model;

    private String description;

    private Category category;

    private BigDecimal price;
}
