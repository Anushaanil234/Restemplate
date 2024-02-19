package com.finalexample.WireMock.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:test/resources/application.yml")
public class TestConfig {

    @Value("${wiremock.port}")
    private int wiremockPort;

    @Value("${test.base-url}")
    private String baseUrl;

    @Value("${test.product-endpoint}")
    private String productEndpoint;

    @Value("${test.product-id-endpoint}")
    private String productIdEndpoint;

    @Value("${test.purchase-endpoint}")
    private String purchaseEndpoint;

    @Value("${test.add-product-endpoint}")
    private String addProductUrl;

    @Value("${test.update-product-endpoint}")
    private String updateProductUrl;

    @Value("${test.purchase-history-url}")
    private String purchaseHistoryUrl;

    @Value("${test.delete-product-url}")
    String deleteProductUrl;

    public int getWiremockPort() {
        return wiremockPort;
    }

    public void setWiremockPort(int wiremockPort) {
        this.wiremockPort = wiremockPort;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getProductEndpoint() {
        return productEndpoint;
    }

    public void setProductEndpoint(String productEndpoint) {
        this.productEndpoint = productEndpoint;
    }

    public String getProductIdEndpoint() {
        return productIdEndpoint;
    }

    public void setProductIdEndpoint(String productIdEndpoint) {
        this.productIdEndpoint = productIdEndpoint;
    }

    public String getPurchaseEndpoint() {
        return purchaseEndpoint;
    }

    public void setPurchaseEndpoint(String purchaseEndpoint) {
        this.purchaseEndpoint = purchaseEndpoint;
    }

    public String getAddProductUrl() {
        return addProductUrl;
    }

    public void setAddProductUrl(String addProductUrl) {
        this.addProductUrl = addProductUrl;
    }

    public String getUpdateProductUrl() {
        return updateProductUrl;
    }

    public void setUpdateProductUrl(String updateProductUrl) {
        this.updateProductUrl = updateProductUrl;
    }

    public String getPurchaseHistoryUrl() {
        return purchaseHistoryUrl;
    }

    public void setPurchaseHistoryUrl(String purchaseHistoryUrl) {
        this.purchaseHistoryUrl = purchaseHistoryUrl;
    }

    public String getDeleteProductUrl() {
        return deleteProductUrl;
    }

    public void setDeleteProductUrl(String deleteProductUrl) {
        this.deleteProductUrl = deleteProductUrl;
    }
}
