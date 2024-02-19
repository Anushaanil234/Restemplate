package Vendingmachine.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;



public class PurchaseInputDTO {
    @NotNull
    private final int productId;

    @NotNull
    private final int price;

    @NotNull
    private final int quantity;

    @JsonCreator
    public static PurchaseInputDTO create(
            @JsonProperty("productId") int productId,
            @JsonProperty("price") int price,
            @JsonProperty("quantity") int quantity) {
        return builder()
                .productId(productId)
                .price(price)
                .quantity(quantity)
                .build();
    }

    public PurchaseInputDTO(PurchaseInputBuilder purchaseInputBuilder) {
        this.productId = purchaseInputBuilder.productId;
        this.price = purchaseInputBuilder.price;
        this.quantity = purchaseInputBuilder.quantity;
    }

    public static PurchaseInputBuilder builder() {
        return new PurchaseInputBuilder();
    }

    public int getProductId() {
        return productId;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public static class PurchaseInputBuilder {
        private int productId;
        private int price;
        private int quantity;

        public PurchaseInputBuilder() {
        }

        public PurchaseInputBuilder productId(int productId) {
            this.productId = productId;
            return this;
        }

        public PurchaseInputBuilder price(int price) {
            this.price = price;
            return this;
        }

        public PurchaseInputBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public PurchaseInputDTO build() {
            return new PurchaseInputDTO(this);
        }
    }
}
