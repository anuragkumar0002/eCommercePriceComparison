package com.backendserver1.BackendServer1.Model;

import com.backendserver1.BackendServer1.Model.Category;
import com.backendserver1.BackendServer1.Model.Image;
import com.backendserver1.BackendServer1.Model.MarketingPrice;
import com.backendserver1.BackendServer1.Model.Price;
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
