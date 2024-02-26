package Vendingmachine.controller;

import Vendingmachine.DTO.CustomerInputDTO;
import Vendingmachine.DTO.InventoryDTO;
import Vendingmachine.DTO.VendingMachineOutputDTO;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public List<InventoryDTO> getListOfAllInventory() {
        String url = baseUrl + getAllInventoryUrl;
        log.debug("Request URL: {}", url);

        ResponseEntity<List<InventoryDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<InventoryDTO>>() {}
        );
        List<InventoryDTO> inventoryList = response.getBody();
        return inventoryList != null ? inventoryList : Collections.emptyList();
    }


    @GetMapping("/product/{id}")
    public InventoryDTO getProductById(@PathVariable int id) {
        String url = baseUrl + getProductByIdUrl +"/"+ id;
        return restTemplate.getForObject(url, InventoryDTO.class);
    }

    @PutMapping("/purchase-product")
    @Operation(summary = "Purchasing the product")
    public VendingMachineOutputDTO productPurchase(@RequestBody CustomerInputDTO customerInputDTO) {
        String url = baseUrl + purchaseProductUrl;
        log.info("Number of products to purchase: {}", customerInputDTO.getProducts().size());
        // Use this.purchaseProductUrl
        ResponseEntity<VendingMachineOutputDTO> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(customerInputDTO),
                VendingMachineOutputDTO.class
        );
        return response.getBody();
    }

}
