package com.backendserver2.BackendServer2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

//import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
public class DealItem {
    @Id
    private Long itemid;

    private String productTitle;
    private String size;
    private String brand;
    private int stock;
    private String dealStartDate;
    private String dealEndDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @OneToOne(cascade = CascadeType.ALL)
    private MarketingPrice marketingPrice;

    @OneToOne(cascade = CascadeType.ALL)
    private Price price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

}
