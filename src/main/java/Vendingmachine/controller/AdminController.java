package Vendingmachine.controller;

import Vendingmachine.DTO.InventoryDTO;
import Vendingmachine.model.InitialBalanceAndPurchaseHistory;
import Vendingmachine.model.Inventry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Tag(name = "ADMIN PROCESS")
public class AdminController {

    public RestTemplate restTemplate;
    private final String baseUrl;
    private final String purchaseHistoryUrl;
    private final String addProductUrl;
    private final String updateProductUrl;
    private final String deleteProductUrl;

    public AdminController(
            RestTemplate restTemplate,
            @Value("${app.endpoints.base-url}") String baseUrl,
            @Value("${app.endpoints.purchase-history}") String purchaseHistoryUrl,
            @Value("${app.endpoints.add-product}") String addProductUrl,
            @Value("${app.endpoints.update-product}") String updateProductUrl,
            @Value("${app.endpoints.delete-product}") String deleteProductUrl
    ) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.purchaseHistoryUrl = purchaseHistoryUrl;
        this.addProductUrl = addProductUrl;
        this.updateProductUrl = updateProductUrl;
        this.deleteProductUrl = deleteProductUrl;
    }

    @GetMapping("/purchase-history")
    @Operation(summary = "Purchase a Product")
    public List<InitialBalanceAndPurchaseHistory> getListOfAllPurchaseHistory() {
        String url = baseUrl + purchaseHistoryUrl;
        ParameterizedTypeReference<List<InitialBalanceAndPurchaseHistory>> responseType =
                new ParameterizedTypeReference<>() {
                };
        return Optional.ofNullable(restTemplate.exchange(url, HttpMethod.GET, null, responseType))
                .map(ResponseEntity::getBody)
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.toList());
    }

    @PostMapping("/add-product")
    @Operation(summary = "Adding a Product")
    public String saveInventory(@RequestBody InventoryDTO e) {
        String url = baseUrl + addProductUrl;
        restTemplate.postForEntity(url, e, String.class);
        return "Product added successfully";
    }

    @PutMapping("/update-product/{id}")
    @Operation(summary = "Update a Product")
    public String updateInventory(@RequestBody Inventry e, @PathVariable Integer id) {
        String url = baseUrl + updateProductUrl + "/" + id;
        return Optional.ofNullable(restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(e), String.class))
                .map(ResponseEntity::getBody)
                .orElse("Product updated successfully");
    }

    @DeleteMapping("/products/{id}")
    @Operation(summary = "Delete Product by id ")
    public String deleteProductById(@PathVariable int id) {
        String url = baseUrl + deleteProductUrl+ "/" + id;
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
            return Optional.ofNullable(response)
                    .map(ResponseEntity::getBody)
                    .orElse("Product deleted successfully");
        } catch (RestClientException e) {
            return "Error deleting product: " + e.getMessage();
        }
    }
}