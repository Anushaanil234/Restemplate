package Vendingmachine.controller;

import Vendingmachine.DTO.InventoryDTO;
import Vendingmachine.model.Inventry;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
public class AdminController {

    public RestTemplate restTemplate;

    @Value("${app.endpoints.purchase-history}")
    public String purchaseHistoryUrl;

    @Value("${app.endpoints.add-product}")
    public String addProductUrl;

    @Value("${app.endpoints.update-product}")
    public String updateProductUrl;

    @Value("${app.endpoints.delete-product}")
    private String deleteProductUrl;

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    // Updated constructor
    public AdminController(
            RestTemplate restTemplate,
            @Value("${app.endpoints.purchase-history}") String purchaseHistoryUrl,
            @Value("${app.endpoints.add-product}") String addProductUrl,
            @Value("${app.endpoints.update-product}") String updateProductUrl,
            @Value("${app.endpoints.delete-product}") String deleteProductUrl
    ) {
        this.restTemplate = restTemplate;
        this.purchaseHistoryUrl = purchaseHistoryUrl;
        this.addProductUrl = addProductUrl;
        this.updateProductUrl = updateProductUrl;
        this.deleteProductUrl = deleteProductUrl;
    }

    @GetMapping("/purchase-history")
    @Operation(summary = "List of purchase-history")
    public List getPurchaseHistoryFromService() {
        if (restTemplate == null || purchaseHistoryUrl == null) {
            // Handle the null case, e.g., throw an exception or return an empty list
            return Collections.emptyList();
        }
        return restTemplate.getForObject(purchaseHistoryUrl, List.class);
    }

    @DeleteMapping("/product/{id}")
    @Operation(summary = "Delete Product ")
    public String deleteProductById(@PathVariable int id) {
        Logger log = LoggerFactory.getLogger(AdminController.class);
        String url = deleteProductUrl.replace("{id}", String.valueOf(id));
        try {
            if (restTemplate != null) {
                return restTemplate.exchange(url, HttpMethod.DELETE, null, String.class).getBody();
            } else {
                log.error("RestTemplate is null in deleteProductById method.");
                return "Error: RestTemplate is null.";
            }
        } catch (Exception e) {
            log.error("Exception in deleteProductById method", e);
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/add-product")
    @Operation(summary = "Add product")
    public ResponseEntity<String> saveInventory(@RequestBody InventoryDTO e) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InventoryDTO> request = new HttpEntity<>(e, headers);

        try {
            if (restTemplate != null && addProductUrl != null) {
                ResponseEntity<String> response = restTemplate.postForEntity(addProductUrl, request, String.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    return ResponseEntity.ok(response.getBody());
                } else {
                    return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
                }
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("RestTemplate or URL not properly initialized");
            }
        } catch (Exception ex) {
            log.error("Exception in saveInventory method", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getMessage());
        }
    }

    @PutMapping("/update-product/{id}")
    @Operation(summary = "Update a Product")
    public String updateInventory(@RequestBody Inventry e, @PathVariable Integer id) {
        String url = updateProductUrl + "/" + id;
        if (restTemplate == null || updateProductUrl == null) {
            // Handle the null case, e.g., throw an exception or return an error response
            return "Product updated successfully";
        }
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(e), String.class).getBody();
    }

}




