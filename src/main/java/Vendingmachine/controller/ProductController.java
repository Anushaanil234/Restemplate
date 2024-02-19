package Vendingmachine.controller;

import Vendingmachine.DTO.CustomerInputDTO;
import Vendingmachine.DTO.InventoryDTO;
import Vendingmachine.DTO.VendingMachineOutputDTO;
import Vendingmachine.exception.InvalidProductId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@Tag(name = "CUSTOMER PROCESS")
public class ProductController {


    public RestTemplate restTemplate;
    private final String baseUrl;
    private final String getAllInventoryUrl;
    private final String getProductByIdUrl;
    private final String purchaseProductUrl;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    public ProductController(
            RestTemplate restTemplate,
            @Value("${urls.base-url}") String baseUrl,
            @Value("${urls.get-all-inventory}") String getAllInventoryUrl,
            @Value("${urls.get-product-by-id}") String getProductByIdUrl,
            @Value("${urls.purchase-product}") String purchaseProductUrl
    ) {

        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.getAllInventoryUrl = getAllInventoryUrl;
        this.getProductByIdUrl = getProductByIdUrl;
        this.purchaseProductUrl = purchaseProductUrl;
    }

    @GetMapping("/products")
    @Operation(summary = "List of All products")
    public List<InventoryDTO> getListOfAllInventory() {
        ResponseEntity<List<InventoryDTO>> responseEntity = restTemplate.exchange(
                this.getAllInventoryUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<InventoryDTO>>() {
                }
        );
        // Check if the response body is not null before calling getBody()
        if (responseEntity != null && responseEntity.getBody() != null) {
            return responseEntity.getBody();
        } else {
            log.error("Received a null or empty response body.");
            return Collections.emptyList(); // or throw an exception if appropriate
        }
    }

    @GetMapping("/product/{id}")
    @Operation(summary = "Product by Id ")
    public InventoryDTO getProductById(@PathVariable int id) {
        try {
            String url = baseUrl + "/product/{id}";
            return restTemplate.getForObject(url, InventoryDTO.class, id);
        } catch (HttpClientErrorException.NotFound notFoundException) {
            // Catch 404 Not Found error and throw your custom exception
            throw new InvalidProductId("Product with ID " + id + " not found", notFoundException);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to fetch product by ID: " + id);
        }
    }

    @PutMapping("/purchase-product")
    @Operation(summary = "Purchasing the product")
    public VendingMachineOutputDTO productPurchase(@RequestBody CustomerInputDTO customerInputDTO) {
        log.info("Number of products to purchase: {}", customerInputDTO.getProducts().size());
        // Use this.purchaseProductUrl
        ResponseEntity<VendingMachineOutputDTO> response = restTemplate.exchange(
                this.purchaseProductUrl,
                HttpMethod.PUT,
                new HttpEntity<>(customerInputDTO),
                VendingMachineOutputDTO.class
        );
        return response.getBody();
    }


}