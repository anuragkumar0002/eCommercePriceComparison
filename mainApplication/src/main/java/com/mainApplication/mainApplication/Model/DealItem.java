package com.mainApplication.mainApplication.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealItem {
    private Long itemid;
    private String productTitle;
    private String size;
    private String brand;
    private int stock;
    private String dealStartDate;
    private String dealEndDate;
    private Image image;
    private MarketingPrice marketingPrice;
    private Price price;

    public DealItem(long itemid, String productTitle, String size, String brand, Image url, MarketingPrice marketingPrice, Price price, int stock, String startDate, String endDate) {

    }
}

