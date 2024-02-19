package com.finalexample.WireMock.admin

import Vendingmachine.DTO.InventoryDTO
import Vendingmachine.controller.AdminController
import Vendingmachine.model.Inventry
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.context.TestPropertySource
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

@TestPropertySource(locations = "classpath:test/resources/application.yml")
class AdminControllerSpec extends Specification {

    @Shared
    RestTemplate restTemplateMock = Mock()

    @Shared
    TestConfig testConfig = new TestConfig()

    @Subject
    AdminController adminController

    WireMockServer wireMockServer

    def setup() {
        restTemplateMock = Mock()

        adminController = new AdminController(
                restTemplateMock,
                testConfig.purchaseHistoryUrl,
                testConfig.addProductUrl,
                testConfig.deleteProductUrl,
                testConfig.updateProductUrl
        )

        adminController.restTemplate = restTemplateMock

        wireMockServer = new WireMockServer(testConfig.wiremockPort)
        wireMockServer.start()
        WireMock.configureFor("localhost", wireMockServer.port())
    }

    def "should save product"() {
        given:
        def inventoryDTO = new InventoryDTO()
        def expectedUrl = "${testConfig.baseUrl}${testConfig.addProductUrl}"
        def filePath = "test/resources/__files/saveresponse.json"

        // Mock the behavior of RestTemplate with response from file
        wireMockServer.stubFor(WireMock.post(WireMock.urlEqualTo("/add-product"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile(filePath)))

        restTemplateMock.postForEntity(expectedUrl, _, String) >> ResponseEntity.ok("Product added successfully")

        when:
        def result = adminController.saveInventory(inventoryDTO)

        then:
        result == "Product added successfully"
    }

    def "should update product by id"() {
        given:
        def inventry = new Inventry()
        def productId = 1
        def expectedUrl = "${testConfig.baseUrl}${testConfig.getUpdateProductUrl()}".replace("{id}", productId.toString())

        def filePath = "test/resources/__files/updateresponse.json"

        wireMockServer.stubFor(WireMock.put(WireMock.urlEqualTo(expectedUrl))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile(filePath)))

        restTemplateMock.exchange(expectedUrl, HttpMethod.PUT, _, String) >> ResponseEntity.ok("Product updated successfully")

        when:
        def result = adminController.updateInventory(inventry, productId)

        then:
        result == "Product updated successfully"
    }


    def "should return the purchase history"() {
        given:
        def expectedUrl = "${testConfig.baseUrl}${testConfig.purchaseHistoryUrl}"
        def filePath = "test/resources/__files/purchasehistory.json"

        // Mock the behavior of RestTemplate with response from file
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo(expectedUrl))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile(filePath)))

    when:
    def result = adminController.getPurchaseHistoryFromService()

    then:
    result != null

}

    def "should delete product by id"() {
        given:
        def productId = 1
        def expectedUrl = "${testConfig.baseUrl}${testConfig.deleteProductUrl}".replace("{id}", productId.toString())

        def filePath = "test/resources/__files/delete.json"

        wireMockServer.stubFor(WireMock.delete(WireMock.urlEqualTo(expectedUrl))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile(filePath)))

        restTemplateMock.exchange(expectedUrl, HttpMethod.DELETE, _, String) >> ResponseEntity.ok("Product deleted successfully")

        when:
        def result = adminController.deleteProductById(productId)

        then:
        result == "Product deleted successfully"
    }

}