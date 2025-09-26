
package paysecure.common.library;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class LoggingContext {

    private static final org.slf4j.Logger apiLogger = LoggerFactory.getLogger("API_LOGGER");
    private static final org.slf4j.Logger dbLogger = LoggerFactory.getLogger("DB_LOGGER");
    private static final int MAX_LOG_BODY_SIZE = 5000; // 5KB

    @Autowired
    GlobalLoggingContext globalContext;

    private void putMdc(String action) {
        globalContext.applyToMdc();
        RequestLoggingContext.getCurrentContext().applyToMdc(action);
    }

    public static String truncateIfLong(String message) {
        if(message.length() > MAX_LOG_BODY_SIZE){
            return message.substring(0, MAX_LOG_BODY_SIZE) + "...[TRUNCATED]" ;
        }
        return message;
    }

    public void apiLog(String action, String message) {
        if (!apiLogger.isInfoEnabled()) return;
        putMdc(action);
        apiLogger.info(truncateIfLong(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

    public void dbLog(String action, String message) {
        if (!dbLogger.isInfoEnabled()) return;

        putMdc(action);
        dbLogger.info(truncateIfLong(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

    public void infoLogT(Logger logger, String action, String message) {
        if (!logger.isInfoEnabled()) return;
        putMdc(action);
        logger.info(truncateIfLong(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

    public void debugLogT(Logger logger, String action, String message) {
        if (!logger.isDebugEnabled()) return;
        putMdc(action);
        logger.debug(truncateIfLong(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

    public void errorLogT(Logger logger, String action, String message) {
        if (!logger.isErrorEnabled()) return;
        putMdc(action);
        logger.error(truncateIfLong(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

    public void warnLogT(Logger logger, String action, String message) {
        if (!logger.isWarnEnabled()) return;
        putMdc(action);
        logger.warn(truncateIfLong(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

    public void criticalLogT(Logger logger, String action, String message) {
        putMdc(action);
        logger.error("[CRITICAL] " + truncateIfLong(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

    public <T> void infoLogV(Logger logger, String action, T message) {
        if (!logger.isInfoEnabled()) return;
        putMdc(action);
        logger.info(WhiteListedKeys.filteredWhiteListedFields(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

    public <T> void debugLogV(Logger logger, String action, T message) {
        if (!logger.isDebugEnabled()) return;
        putMdc(action);
        logger.debug(WhiteListedKeys.filteredWhiteListedFields(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

    public <T> void errorLogV(Logger logger, String action, T message) {
        if (!logger.isErrorEnabled()) return;
        putMdc(action);
        logger.error(WhiteListedKeys.filteredWhiteListedFields(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

    public <T> void warnLogV(Logger logger, String action, T message) {
        if (!logger.isWarnEnabled()) return;
        putMdc(action);
        logger.warn(WhiteListedKeys.filteredWhiteListedFields(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

    public <T> void criticalLogV(Logger logger, String action, T message) {
        putMdc(action);
        logger.error("[CRITICAL] " + WhiteListedKeys.filteredWhiteListedFields(message));
        MDC.remove("action");  // 👈 only remove action, keep global ones

    }

}
