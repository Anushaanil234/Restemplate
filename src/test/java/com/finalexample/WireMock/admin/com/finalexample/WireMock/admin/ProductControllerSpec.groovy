package com.finalexample.WireMock.admin

import Vendingmachine.DTO.CustomerInputDTO
import Vendingmachine.DTO.InventoryDTO
import Vendingmachine.DTO.PurchaseInputDTO
import Vendingmachine.DTO.VendingMachineOutputDTO
import Vendingmachine.controller.ProductController
import Vendingmachine.model.Inventry
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.TestPropertySource
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

@TestPropertySource(locations = "classpath:test/resources/application.yml")
class ProductControllerSpec extends Specification {

    @Shared
    RestTemplate restTemplateMock = Mock()

    @Shared
    TestConfig testConfig = new TestConfig()

    @Subject
    ProductController productController

    WireMockServer wireMockServer

    def setup() {
        restTemplateMock = Mock()

        productController = new ProductController(
                restTemplateMock,
                testConfig.baseUrl,
                testConfig.productEndpoint,
                testConfig.productIdEndpoint,
                testConfig.purchaseEndpoint
        )
        productController.restTemplate = restTemplateMock

        wireMockServer = new WireMockServer(testConfig.wiremockPort)
        wireMockServer.start()
        WireMock.configureFor("localhost", wireMockServer.port())
    }

    def "return list of all inventory"() {
        setup()

        given:
        def expectedInventoryList = [
                new InventoryDTO(
                        productId: 1,
                        name: "dietcoke",
                        productPrice: 35,
                        productInventoryCount: 2
                ),
        ]
        def filePath = "test/resources/__files/getListOfAllInventory.json"

        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/products"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile(filePath)))

        restTemplateMock.exchange(
                "${testConfig.baseUrl}${testConfig.productEndpoint}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<InventoryDTO>>() {}
        ) >> new ResponseEntity<>(expectedInventoryList, HttpStatus.OK)

        when:
        def result = productController.getListOfAllInventory()

        then:
        result == expectedInventoryList
    }

    def "return the product by id"() {
        setup()

        given:
        def expectedInventory = null
        def filePath = "test/resources/__files/getProductById.json"

        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/product/1"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile(filePath)))

        restTemplateMock.getForObject("${testConfig.baseUrl}${testConfig.productIdEndpoint}".replace("{id}", "1"), InventoryDTO.class) >> expectedInventory

        when:
        def result = productController.getProductById(1)

        then:
        result == expectedInventory

    }

    def "should purchase the product"() {
        given:
        def customerInputDTO = CustomerInputDTO.create([
                new PurchaseInputDTO.PurchaseInputBuilder()
                        .productId(1)
                        .price(35)
                        .quantity(2)
                        .build()
        ])
        def expectedVendingMachineOutputDTO = new VendingMachineOutputDTO()
        def filePath = "test/resources/__files/purchaseproduct.json"

        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/purchase-product"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile(filePath)))

        restTemplateMock.exchange(
                "${testConfig.baseUrl}${testConfig.purchaseEndpoint}",
                HttpMethod.PUT,
                new HttpEntity<>(customerInputDTO),
                VendingMachineOutputDTO.class
        ) >> new ResponseEntity<>(expectedVendingMachineOutputDTO, HttpStatus.OK)

        when:
        def result = productController.productPurchase(customerInputDTO)

        then:
        result == expectedVendingMachineOutputDTO
    }



}

