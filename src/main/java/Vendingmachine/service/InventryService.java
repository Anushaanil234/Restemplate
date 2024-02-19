//package Vendingmachine.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import Vendingmachine.config.InventryConfig;
//import Vendingmachine.model.Inventry;
//
//@Service
//public class InventryService {
//
//    private final Inventry inventry;
//
//    @Autowired
//    public InventryService(Inventry inventryConfig) {
//        this.inventry = inventry;
//    }
//
//    public Inventry getDefaultInventry() {
//        Inventry defaultInventry = inventry.inventry();
//
//        // Set the default values if they are not provided in the configuration
//        if (defaultInventry.getName() == null) {
//            defaultInventry.setName("DefaultProduct");
//        }
//
//        return defaultInventry;
//    }
//}
