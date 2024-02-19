package Vendingmachine.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingController {

    public static Logger logger = LoggerFactory.getLogger(LoggingController.class);
    public void index() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
    }
}