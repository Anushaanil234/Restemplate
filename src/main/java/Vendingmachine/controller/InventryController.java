package Vendingmachine.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Vendingmachine.model.Inventry;

@RestController
@RequestMapping("/api")
public class InventryController {

    @Value("${vendingmachine.inventry.defaultProductId}")
    private int defaultProductId;

    @Value("${vendingmachine.inventry.defaultName}")
    private String defaultName;

    @Value("${vendingmachine.inventry.defaultProductPrice}")
    private int defaultProductPrice;

    @Value("${vendingmachine.inventry.defaultProductInventoryCount}")
    private int defaultProductInventoryCount;

    @GetMapping("/inventry-config")
    public Inventry getInventryConfig() {
        Inventry inventry = new Inventry();
        inventry.setProductId(defaultProductId);
        inventry.setName(defaultName);
        inventry.setProductPrice(defaultProductPrice);
        inventry.setProductInventoryCount(defaultProductInventoryCount);

        return inventry;
    }
}

