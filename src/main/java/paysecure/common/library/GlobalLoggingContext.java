package paysecure.common.library;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class GlobalLoggingContext {

    @Value("${spring.boot.config.type}")
    private String environment;

    @Value("${spring.boot.config.applicationName}")
    private String applicationName;

    @PostConstruct
    public void init() {
        MDC.put("applicationName", applicationName);
        MDC.put("environment", environment);
    }

    public void applyToMdc() {
        MDC.put("applicationName", applicationName);
        MDC.put("environment", environment);
    }
}
