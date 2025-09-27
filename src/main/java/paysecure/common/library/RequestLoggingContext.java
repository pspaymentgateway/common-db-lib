package paysecure.common.library;


import org.slf4j.MDC;
import java.util.ArrayList;
import java.util.List;


public class RequestLoggingContext {

    private static final ThreadLocal<RequestLoggingContext> contextHolder =
            ThreadLocal.withInitial(RequestLoggingContext::new);

    private String merchantName;
    private String midName;
    private String bankName;
    private String flowName;
    private final List<String> paymentFlows = new ArrayList<>(); // always initialized
    private String purchaseId;
    private String customerId;

    // NEW: message counter
    private int messageCounter = 0;

    public static RequestLoggingContext getCurrentContext() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
        MDC.clear(); // cleanup MDC too
    }

    public void applyToMdc(String action) {

        if(WhiteListedKeys.checkIfWhiteListed("merchantName")){
                MDC.put("merchantName", merchantName);
        }else{
            MDC.put("merchantName", "FILTERED");
        }

        if(WhiteListedKeys.checkIfWhiteListed("midName")){
                MDC.put("midName", midName);
        }else{
            MDC.put("midName", "FILTERED");
        }

        if(WhiteListedKeys.checkIfWhiteListed("bankName")){
                MDC.put("bankName", bankName);
        }else{
            MDC.put("bankName", "FILTERED");
        }

        if(WhiteListedKeys.checkIfWhiteListed("flowName")){
                MDC.put("flowName", flowName);
        }else{
            MDC.put("flowName", "FILTERED");
        }

        if(WhiteListedKeys.checkIfWhiteListed("purchaseId")){
                MDC.put("purchaseId", purchaseId);
        }else{
            MDC.put("purchaseId", "FILTERED");
        }

        if(WhiteListedKeys.checkIfWhiteListed("customerId")){
                MDC.put("customerId", customerId);
        }else{
            MDC.put("customerId", "FILTERED");
        }

        if (!paymentFlows.isEmpty()) {
            MDC.put("paymentFlows", paymentFlows.toString());
        }
        
        // increment message counter every log
        messageCounter++;
        MDC.put("message_number", String.valueOf(messageCounter));

        MDC.put("action", action);
    }

    // --- Getters ---
    public String getMerchantName() { return merchantName; }
    public String getMidName() { return midName; }
    public String getBankName() { return bankName; }
    public String getFlowName() { return flowName; }
    public List<String> getPaymentFlows() { return paymentFlows; }
    public String getPurchaseId() { return purchaseId; }
    public String getCustomerId() { return customerId; }

    // --- Setters ---
    public void setMerchantName(String merchantName) { this.merchantName = merchantName; }
    public void setBankName(String bankName) { this.bankName = bankName; }
    public void setPurchaseId(String purchaseId) { this.purchaseId = purchaseId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setFlowName(String flowName) { this.flowName = flowName; }

    public void addPaymentFlow(String paymentFlow) {
        if (paymentFlow != null) {
            this.paymentFlows.add(paymentFlow);
        }
    }

}

// HOW TO USE Logging Context

// RequestLoggingContext ctx = RequestLoggingContext.getCurrentContext();
// ctx.setMerchantName("Flipkart");
// ctx.setBankName("HDFC");
// ctx.setPurchaseId("TXN123");
// ctx.setCustomerId("CUST567");

// loggingService.apiLog("START_PAYMENT", "Payment started");
// loggingService.apiLog("VERIFY", "Bank verification done");
