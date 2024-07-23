package com.backendserver3.BackendServer3.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MarketingPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private OrginalPrice originalPrice;

    private String discountPercentage;

    @OneToOne(cascade = CascadeType.ALL)
    private  DiscountAmount discountAmount;

    private String priceTreatment;
}
