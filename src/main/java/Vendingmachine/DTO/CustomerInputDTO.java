package Vendingmachine.DTO;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
//public class CustomerInputDTO {
//
//    @NotNull
//    @Valid
//    private final List<@Valid PurchaseInputDTO> products;
//
//    @JsonCreator
//    public static CustomerInputDTO create(@JsonProperty("products") List<PurchaseInputDTO> products) {
//        return new CustomerInputDTO(products);
//    }
//
//    public CustomerInputDTO(List<PurchaseInputDTO> products) {
//        this.products = products;
//    }
//    public List<PurchaseInputDTO> getProducts() {
//        return products;
//    }
//}

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CustomerInputDTO {

    @NotNull
    @Valid
    private final List<@Valid PurchaseInputDTO> products;

    @JsonCreator
    public static CustomerInputDTO create(@JsonProperty("products") List<PurchaseInputDTO> products) {
        return new CustomerInputDTO(products);
    }

    public CustomerInputDTO(List<PurchaseInputDTO> products) {
        this.products = products;
    }

    public List<PurchaseInputDTO> getProducts() {
        return products;
    }
}
