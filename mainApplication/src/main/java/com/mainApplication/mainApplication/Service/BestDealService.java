package com.mainApplication.mainApplication.Service;

import com.mainApplication.mainApplication.Model.Category;
import com.mainApplication.mainApplication.Model.DealItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BestDealService {

    @Autowired
    private DealService dealService;

    public List<DealItem> getBestDeals(String category) throws Exception {
        CompletableFuture<Category> backend1 = dealService.fetchDealsFromBackend1(category);
        CompletableFuture<Category> backend2 = dealService.fetchDealsFromBackend2(category);
        CompletableFuture<Category> backend3 = dealService.fetchDealsFromBackend3(category);

        CompletableFuture.allOf(backend1, backend2, backend3).join();

        List<DealItem> allDeals = Stream.of(backend1.get(), backend2.get(), backend3.get())
                .flatMap(categoryObj -> categoryObj.getDealItems() != null ? categoryObj.getDealItems().stream() : Stream.empty())
                .collect(Collectors.toList());

//        System.out.println(allDeals);

        ZonedDateTime date = ZonedDateTime.now();

        List<DealItem> filteredDeals = allDeals.stream()
                .filter(deal -> deal.getStock() > 0)
                .filter(deal -> {
                    ZonedDateTime dealStartDate = ZonedDateTime.parse(deal.getDealStartDate());
                    ZonedDateTime dealEndDate = ZonedDateTime.parse(deal.getDealEndDate());
                    return !date.isBefore(dealStartDate) && !date.isAfter(dealEndDate);
                })
                .sorted((deal1, deal2) -> {
                    int discountComparison = Double.compare(deal2.getMarketingPrice().getDiscountPercentage(), deal1.getMarketingPrice().getDiscountPercentage());

                    if (discountComparison != 0) {
                        return discountComparison;
                    } else {
                        return Double.compare(deal1.getPrice().getValue(), deal2.getPrice().getValue());
                    }
                })
                .collect(Collectors.toList());

        return filteredDeals;
    }
}



