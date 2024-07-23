package com.mainApplication.mainApplication.Service;

import com.mainApplication.mainApplication.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Service
public class DealService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public CompletableFuture<Category> fetchDealsFromBackend1(String category) {
        CompletableFuture<Category> data = webClientBuilder.build()
                .get()
                .uri("http://localhost:9097/api/categories/amazon/" + category)
                .retrieve()
                .bodyToMono(Category.class)
                .toFuture();

//        data.thenAccept(categoryData -> {
//            System.out.println("Category data: " + categoryData);
//        });
        return data;
    }


    public CompletableFuture<Category> fetchDealsFromBackend2(String category) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:9099/api/categories/walmart/" + category)
                .retrieve()
                .bodyToMono(Category.class)
                .toFuture();
    }

    public CompletableFuture<Category> fetchDealsFromBackend3(String category) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:9098/api/categories/ebay/" + category)
                .retrieve()
                .bodyToMono(Category.class)
                .toFuture();
    }
}
