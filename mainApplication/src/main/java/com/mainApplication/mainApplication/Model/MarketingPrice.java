package com.mainApplication.mainApplication.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketingPrice {
    private Price originalPrice;
    private double discountPercentage;
    private Price discountAmount;
    private String priceTreatment;

}
