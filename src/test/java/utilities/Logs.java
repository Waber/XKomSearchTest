package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logs {
    private final static Logger log = LoggerFactory.getLogger("XKomTests");

    public static Logger getLog() {
        return log;
    }
}
