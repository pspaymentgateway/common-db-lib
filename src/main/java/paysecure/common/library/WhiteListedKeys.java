package paysecure.common.library;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;

import java.util.*;

public class WhiteListedKeys {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Example whitelist
    private static final Set<String> WHITELIST_KEYS = Collections.unmodifiableSet(
                        new HashSet<>(Arrays.asList("status",
            "amount", 
            "name", 
            "submenu", 
            "data",
            "params",
            "method",
            "url",
            "query_string", 
            "request_headers",
        //    "request_body",
            "response_headers",
           "response_body",
            "client_ip",
            "merchantName",
            "midName",
            "bankName",
            "flowName",
            "purchaseId",
            "customerId",
            "message",
            "parentId",
            "agentId",
            "whiteId",
            "allowedCurr",
            "purchase_id",
            "status",
            "country",
            "extraParam",
            "is_block",
            "isSandBox",
            "isLiveTransAllowed",
            "mid",
            "bankid",
            "pb_id",
            "deno",
            "flag",
            "userId",
            "userName",
            "type",
            "paymentMethod",
            "percentage",
            "category",
            "sql"

        ))
    );

    private static final Set<String> BLACKLIST_ROUTE_FOR_LOGGING = Collections.unmodifiableSet(
                        new HashSet<>(Arrays.asList("/getDownload",
                                    "/getTransactDownload",
                                    "/getTransLogDownload",
                                    "/download",
                                    ".zip",
                                    ".pdf",
                                    ".csv",
                                    ".jsp",
                                    "redirect",
                                    "/payment/",
                                    "/s2spayment",
                                     "/p",
                                    "/payments",
                                    "/npv",
                                    "/depositdetail",
                                    "/depositdetail",
                                    "/showProvider",
                                    "/showProvider",
                                    "/verifydummydeposit",
                                    "/verifydeposit",
                                    "/api/v1",
                                    "/cyberHostedcheckOut",
                                    "/misc",
                                    "/webhook",
                                    "/leadpage_cb",
                                    "/custRedirect",
                                    "/deposit",
                                    "/execute",
                                    "/p2p/v1",
                                    "/p2p/mobile",
                                    "jsp",
                                    "/merchantonboard",
                                    "/OTP",
                                    "/otpverication",
                                    "/addlivepersondetails",
                                    "/chat",
                                    "/chat",
                                    "/validate-merchant",
                                    "/validate-merchant-applePay",
                                    "/GPayScript",
                                    "/ddcsubmit",
                                    "/getCardDetail",
                                    "/pov",
                                    "/custRedirect",
                                    "/mesh",
                                    "/meshMFA",
                                    "/meshPreview",
                                    "/getWhiteLabellogo",
                                    "/serviceConfiguration",
                                    "/payoutSession",
                                    "/cryptoNotify",
                                    "/cryptoPayoutNotify",
                                    "/crypto",
                                    "/payout",
                                    // "/admin/v2",
                                    // "/auth/v2",
                                    // "/rule-engine/v2",
                                    // "/accountSettings",
                                    "/pov",
                                    "/iban",
                                    "/convertToCrypto",
                                    "/api/v2"
        
        ))
    );

    public static Boolean checkIfBlackListedRoute(String uri, String queryString) {
        if (uri == null) return false;
        String combined = uri + (queryString != null ? "?" + queryString : "");
        return BLACKLIST_ROUTE_FOR_LOGGING.stream().anyMatch(combined::contains);
    }

    public static Boolean checkIfWhiteListed(String fieldName){

        return WHITELIST_KEYS.contains(fieldName);

    }

    @SuppressWarnings("unchecked")
    public static <T> T filteredWhiteListedFields(T input) {
        try {
            JsonNode root;

            if (input instanceof JsonNode) {
                root = (JsonNode) input;
            } else if (input instanceof String) {
                root = objectMapper.readTree((String) input);
            } else {
                root = objectMapper.valueToTree(input);
            }

            JsonNode filtered = filterNode(root);

            if (input instanceof String) {
                return (T) objectMapper.writeValueAsString(filtered);
            } else if (input instanceof JsonNode) {
                return (T) filtered;
            } else {
                return (T) objectMapper.treeToValue(filtered, input.getClass());
            }

        } catch (Exception e) {
            return input; // fallback: original input
        }
    }

    private static JsonNode filterNode(JsonNode node) {
        if (node.isObject()) {
            ObjectNode filtered = objectMapper.createObjectNode();
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String fieldName = entry.getKey();
                JsonNode value = entry.getValue();

                if (WHITELIST_KEYS.contains(fieldName)) {
                    filtered.set(fieldName, filterNode(value)); // recurse
                } else {
                    filtered.set(fieldName, TextNode.valueOf("FILTERED"));
                }
            }
            return filtered;
        } else if (node.isArray()) {
            ArrayNode filteredArray = objectMapper.createArrayNode();
            for (JsonNode element : node) {
                filteredArray.add(filterNode(element));
            }
            return filteredArray;
        } else {
            // Non-container (string, number, boolean, null) → just return
            return node;
        }
    }
}
