//package Vendingmachine.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import Vendingmachine.model.Inventry;
//
//@Configuration
//@ConfigurationProperties(prefix = "vendingmachine.inventry")
//public class InventryConfig {
//
//    private int defaultProductId;
//    private String defaultName;
//    private int defaultProductPrice;
//    private int defaultProductInventoryCount;
//
//    @Bean
//    public Inventry inventry() {
//        return new Inventry(defaultProductId, defaultName, defaultProductPrice, defaultProductInventoryCount);
//    }
//
//    public int getDefaultProductId() {
//        return defaultProductId;
//    }
//
//    public void setDefaultProductId(int defaultProductId) {
//        this.defaultProductId = defaultProductId;
//    }
//
//    public String getDefaultName() {
//        return defaultName;
//    }
//
//    public void setDefaultName(String defaultName) {
//        this.defaultName = defaultName;
//    }
//
//    public int getDefaultProductPrice() {
//        return defaultProductPrice;
//    }
//
//    public void setDefaultProductPrice(int defaultProductPrice) {
//        this.defaultProductPrice = defaultProductPrice;
//    }
//
//    public int getDefaultProductInventoryCount() {
//        return defaultProductInventoryCount;
//    }
//
//    public void setDefaultProductInventoryCount(int defaultProductInventoryCount){
//        this.defaultProductInventoryCount = defaultProductInventoryCount;
//    }
//}
