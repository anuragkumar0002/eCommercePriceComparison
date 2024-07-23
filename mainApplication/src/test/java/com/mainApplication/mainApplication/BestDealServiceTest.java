package com.mainApplication.mainApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.mainApplication.mainApplication.Model.*;
import com.mainApplication.mainApplication.Service.BestDealService;
import com.mainApplication.mainApplication.Service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BestDealServiceTest {

    @Mock
    private DealService dealService;

    @InjectMocks
    private BestDealService bestDealService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBestDeals() throws Exception {
        DealItem deal1 = new DealItem(1L, "Product 1", "L", "BrandA", 10, "2024-06-20T15:26:00.000000Z", "2024-09-30T23:59:59.000000Z", new Image("url1"), new MarketingPrice(new Price(100.0, "USD"), 20.0, new Price(20.0, "USD"), "LIST_PRICE"), new Price(80.0, "USD"));
        DealItem deal2 = new DealItem(2L, "Product 2", "M", "BrandB", 0, "2024-06-20T15:26:00.000000Z", "2024-09-30T23:59:59.000000Z", new Image("url2"), new MarketingPrice(new Price(200.0, "USD"), 10.0, new Price(20.0, "USD"), "LIST_PRICE"), new Price(180.0, "USD")); // stock 0
        DealItem deal3 = new DealItem(3L, "Product 3", "S", "BrandC", 5, "2024-06-20T15:26:00.000000Z", "2024-09-30T23:59:59.000000Z", new Image("url3"), new MarketingPrice(new Price(300.0, "USD"), 30.0, new Price(90.0, "USD"), "LIST_PRICE"), new Price(210.0, "USD"));

        Category category1 = new Category("Shirts", Arrays.asList(deal1, deal2));
        Category category2 = new Category("Shirts", Collections.singletonList(deal3));

        when(dealService.fetchDealsFromBackend1("Shirts")).thenReturn(CompletableFuture.completedFuture(category1));
        when(dealService.fetchDealsFromBackend2("Shirts")).thenReturn(CompletableFuture.completedFuture(new Category("Shirts", Collections.emptyList())));
        when(dealService.fetchDealsFromBackend3("Shirts")).thenReturn(CompletableFuture.completedFuture(category2));

        List<DealItem> result = bestDealService.getBestDeals("Shirts");

        assertEquals(2, result.size());
        assertEquals(deal3, result.get(0)); // Highest discount
        assertEquals(deal1, result.get(1)); // Next highest discount
    }
}
