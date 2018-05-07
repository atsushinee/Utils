package logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackDemo {
    private static final Logger logger = LoggerFactory.getLogger(LogBackDemo.class);

    public static void main(String[] args) {
        logger.info("hello");
    }
}
