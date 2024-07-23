package com.mainApplication.mainApplication.Controller;

import com.mainApplication.mainApplication.Model.DealItem;
import com.mainApplication.mainApplication.Service.BestDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/deals")
public class DealController {

    @Autowired
    private BestDealService bestDealService;

    @GetMapping("/{categoryname}")
    public List<DealItem> getBestDeals(@PathVariable String categoryname) throws Exception {
        return bestDealService.getBestDeals(categoryname);
    }
}
