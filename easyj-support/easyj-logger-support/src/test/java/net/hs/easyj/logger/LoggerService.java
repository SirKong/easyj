package net.hs.easyj.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gavin
 * @create 15/9/4
 */
public class LoggerService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void info(String message) {
        logger.info(message);
    }

}
