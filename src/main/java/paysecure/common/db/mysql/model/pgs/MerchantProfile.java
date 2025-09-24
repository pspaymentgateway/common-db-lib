package paysecure.common.db.mysql.model.pgs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@JacksonStdImpl
@AllArgsConstructor
public class MerchantProfile {

	private long id;
	private long login_id;
	@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/_ ]*$")
	@NotEmpty
	private String name;
	@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/_ ]*$")
	@NotEmpty
	private String legal_name;
	private String trading_address;
	private String corporate_address;
	private String trading_country;
	private String coroporate_country;
	private String trading_state;
	private String trading_postal;
	private String corporate_postal;
	private String corporate_state;
	private String business_phone;
	private String contact_phone;
	private String contact_name;
	private String reg_no;
	private String email_address;
	private String website_url;
	private String trading_start_date;
	private String date_of_incorporate;
	private String officer_title;
	private String officer_name;
	private String default_timezone;
	private String default_curr;
	private int is_sandbox;
	private int isLiveTransAllowed;
	private int isCardSeacrhAllowed;
	private int agentId;
	private int parentId;
	private int whiteId = -1;
	private String allowedApi = "";
	private int checkWhiteList = 1;
	private String allowedPaymentMethod = "CREDITCARD";
	private String redirectType = "POST";

	private String qb_merchant_id = "";
	private String qb_merchant_signupform = "";
	private String qb_merchant_id_status = "";

	private int allowedTrustScore;

	private int checkBlackList = 1;

	private int applyConversionRiskFactor = 1;

	private String allowedCurr = "";

	private String allowedDescriptor = "";
	private int secondToWaitPaymentExec = -1;
	private String cashier_template = "1";
	private String trxType;
	private String checkoutURL = "";
	private String balanceURL = "";
	private int isLivePersonAllowed = 0;
	private String profilePassword = "";
	private int over_under_paid_allowed = 0;
	private int maximumRiskCapping;
	private int conversionAllowed = 1;
	private float conversionRiskFactor = 0.00f;
	private float otherConversionRiskFactor = -1.00f;
	private float otherMaxAmount = 0.00f;

	private float sameConversionRiskFactor = -1.00f;
	private int sameMaximumRiskCapping = -1;

	private int lastInvoiceNumber;
	
	private String interacEmail;

	@JsonProperty("threeds_allowed")
	private int threeds_allowed;
	@JsonProperty("network_token_allowed")
	private int network_token_allowed;

	private Boolean check_skip_risk_factor;

	public float getSameConversionRiskFactor() {
		return sameConversionRiskFactor;
	}

	public void setSameConversionRiskFactor(float sameConversionRiskFactor) {
		this.sameConversionRiskFactor = sameConversionRiskFactor;
	}

	public int getSameMaximumRiskCapping() {
		return sameMaximumRiskCapping;
	}

	public void setSameMaximumRiskCapping(int sameMaximumRiskCapping) {
		this.sameMaximumRiskCapping = sameMaximumRiskCapping;
	}

	public float getConversionRiskFactor() {
		return conversionRiskFactor;
	}

	public void setConversionRiskFactor(float conversionRiskFactor) {
		this.conversionRiskFactor = conversionRiskFactor;
	}

	public float getOtherConversionRiskFactor() {
		return otherConversionRiskFactor;
	}

	public void setOtherConversionRiskFactor(float otherConversionRiskFactor) {
		this.otherConversionRiskFactor = otherConversionRiskFactor;
	}

	public float getOtherMaxAmount() {
		return otherMaxAmount;
	}

	public void setOtherMaxAmount(float otherMaxAmount) {
		this.otherMaxAmount = otherMaxAmount;
	}

	public int getConversionAllowed() {
		return conversionAllowed;
	}

	public void setConversionAllowed(int conversionAllowed) {
		this.conversionAllowed = conversionAllowed;
	}

	public int getAllowedTrustScore() {
		return allowedTrustScore;
	}

	public void setAllowedTrustScore(int allowedTrustScore) {
		this.allowedTrustScore = allowedTrustScore;
	}

	public String getQb_merchant_id() {
		return qb_merchant_id;
	}

	public void setQb_merchant_id(String qb_merchant_id) {
		this.qb_merchant_id = qb_merchant_id;
	}

	public String getQb_merchant_signupform() {
		return qb_merchant_signupform;
	}

	public void setQb_merchant_signupform(String qb_merchant_signupform) {
		this.qb_merchant_signupform = qb_merchant_signupform;
	}

	public String getQb_merchant_id_status() {
		return qb_merchant_id_status;
	}

	public void setQb_merchant_id_status(String qb_merchant_id_status) {
		this.qb_merchant_id_status = qb_merchant_id_status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLogin_id() {
		return login_id;
	}

	public void setLogin_id(long login_id) {
		this.login_id = login_id;
	}

	public String getLegal_name() {
		return legal_name;
	}

	public void setLegal_name(String legal_name) {
		this.legal_name = legal_name;
	}

	public String getTrading_address() {
		return trading_address;
	}

	public void setTrading_address(String trading_address) {
		this.trading_address = trading_address;
	}

	public String getCorporate_address() {
		return corporate_address;
	}

	public void setCorporate_address(String corporate_address) {
		this.corporate_address = corporate_address;
	}

	public String getTrading_country() {
		return trading_country;
	}

	public void setTrading_country(String trading_country) {
		this.trading_country = trading_country;
	}

	public String getCoroporate_country() {
		return coroporate_country;
	}

	public void setCoroporate_country(String coroporate_country) {
		this.coroporate_country = coroporate_country;
	}

	public String getTrading_state() {
		return trading_state;
	}

	public void setTrading_state(String trading_state) {
		this.trading_state = trading_state;
	}

	public String getCorporate_state() {
		return corporate_state;
	}

	public void setCorporate_state(String corporate_state) {
		this.corporate_state = corporate_state;
	}

	public String getTrading_postal() {
		return trading_postal;
	}

	public void setTrading_postal(String trading_postal) {
		this.trading_postal = trading_postal;
	}

	public String getCorporate_postal() {
		return corporate_postal;
	}

	public void setCorporate_postal(String corporate_postal) {
		this.corporate_postal = corporate_postal;
	}

	public String getBusiness_phone() {
		return business_phone;
	}

	public void setBusiness_phone(String business_phone) {
		this.business_phone = business_phone;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}

	public String getWebsite_url() {
		return website_url;
	}

	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		if (email_address != null)
			email_address = email_address.toLowerCase();
		this.email_address = email_address;
	}

	public String getTrading_start_date() {
		return trading_start_date;
	}

	public void setTrading_start_date(String trading_start_date) {
		this.trading_start_date = trading_start_date;
	}

	public String getDate_of_incorporate() {
		return date_of_incorporate;
	}

	public void setDate_of_incorporate(String date_of_incorporate) {
		this.date_of_incorporate = date_of_incorporate;
	}

	public String getOfficer_title() {
		return officer_title;
	}

	public void setOfficer_title(String officer_title) {
		this.officer_title = officer_title;
	}

	public String getOfficer_name() {
		return officer_name;
	}

	public void setOfficer_name(String officer_name) {
		this.officer_name = officer_name;
	}
	/*
	 * public int getFilled() { return filled; }
	 * 
	 * public void setFilled(int filled) { this.filled = filled; }
	 */

	public String getDefault_timezone() {
		return default_timezone;
	}

	public void setDefault_timezone(String default_timezone) {
		this.default_timezone = default_timezone;
	}

	public String getDefault_curr() {
		return default_curr;
	}

	public void setDefault_curr(String default_curr) {
		this.default_curr = default_curr;
	}

	public int getIs_sandbox() {
		return is_sandbox;
	}

	public void setIs_sandbox(int is_sandbox) {
		this.is_sandbox = is_sandbox;
	}

	public int getIsLiveTransAllowed() {
		return isLiveTransAllowed;
	}

	public void setIsLiveTransAllowed(int isLiveTransAllowed) {
		this.isLiveTransAllowed = isLiveTransAllowed;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getWhiteId() {
		return whiteId;
	}

	public void setWhiteId(int whiteId) {
		this.whiteId = whiteId;
	}

	public String getAllowedApi() {
		return allowedApi;
	}

	public void setAllowedApi(String allowedApi) {
		this.allowedApi = allowedApi;
	}

	public int getCheckWhiteList() {
		return checkWhiteList;
	}

	public void setCheckWhiteList(int checkWhiteList) {
		this.checkWhiteList = checkWhiteList;
	}

	public String getAllowedPaymentMethod() {
		return allowedPaymentMethod;
	}

	public void setAllowedPaymentMethod(String allowedPaymentMethod) {
		this.allowedPaymentMethod = allowedPaymentMethod;
	}

	public String getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(String redirectType) {
		this.redirectType = redirectType;
	}

	public int getCheckBlackList() {
		return checkBlackList;
	}

	public void setCheckBlackList(int checkBlackList) {
		this.checkBlackList = checkBlackList;
	}

	public int getApplyConversionRiskFactor() {
		return applyConversionRiskFactor;
	}

	public void setApplyConversionRiskFactor(int applyConversionRiskFactor) {
		this.applyConversionRiskFactor = applyConversionRiskFactor;
	}

	public String getAllowedCurr() {
		return allowedCurr;
	}

	public void setAllowedCurr(String allowedCurr) {
		this.allowedCurr = allowedCurr;
	}

	public String getAllowedDescriptor() {
		return allowedDescriptor;
	}

	public void setAllowedDescriptor(String allowedDescriptor) {
		this.allowedDescriptor = allowedDescriptor;
	}

	public int getSecondToWaitPaymentExec() {
		return secondToWaitPaymentExec;
	}

	public void setSecondToWaitPaymentExec(int secondToWaitPaymentExec) {
		this.secondToWaitPaymentExec = secondToWaitPaymentExec;
	}

	public String getTrxType() {
		return trxType;
	}

	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}

	public String getCashier_template() {
		return cashier_template;
	}

	public void setCashier_template(String cashier_template) {
		this.cashier_template = cashier_template;
	}

	public String getCheckoutURL() {
		return checkoutURL;
	}

	public void setCheckoutURL(String checkoutURL) {
		this.checkoutURL = checkoutURL;
	}

	public int getIsLivePersonAllowed() {
		return isLivePersonAllowed;
	}

	public void setIsLivePersonAllowed(int isLivePersonAllowed) {
		this.isLivePersonAllowed = isLivePersonAllowed;
	}

	public int getIsCardSeacrhAllowed() {
		return isCardSeacrhAllowed;
	}

	public void setIsCardSeacrhAllowed(int isCardSeacrhAllowed) {
		this.isCardSeacrhAllowed = isCardSeacrhAllowed;
	}

	public String getProfilePassword() {
		return profilePassword;
	}

	public void setProfilePassword(String profilePassword) {
		this.profilePassword = profilePassword;
	}

	public int getOver_under_paid_allowed() {
		return over_under_paid_allowed;
	}

	public void setOver_under_paid_allowed(int over_under_paid_allowed) {
		this.over_under_paid_allowed = over_under_paid_allowed;
	}

	public int getMaximumRiskCapping() {
		return maximumRiskCapping;
	}

	public void setMaximumRiskCapping(int maximumRiskCapping) {
		this.maximumRiskCapping = maximumRiskCapping;
	}

	public int getThreeds_allowed() {
		return threeds_allowed;
	}

	public void setThreeds_allowed(int threeds_allowed) {
		this.threeds_allowed = threeds_allowed;
	}

	public int getNetwork_token_allowed() {
		return network_token_allowed;
	}

	public void setNetwork_token_allowed(int network_token_allowed) {
		this.network_token_allowed = network_token_allowed;
	}

	
	public String getBalanceURL() {
		return balanceURL;
	}

	public void setBalanceURL(String balanceURL) {
		this.balanceURL = balanceURL;
	}

	public Boolean getCheck_skip_risk_factor() {
		return check_skip_risk_factor;
	}

	public void setCheck_skip_risk_factor(Boolean check_skip_risk_factor) {
		this.check_skip_risk_factor = check_skip_risk_factor;
	}

	public int getLastInvoiceNumber() {
		return lastInvoiceNumber;
	}

	public void setLastInvoiceNumber(int lastInvoiceNumber) {
		this.lastInvoiceNumber = lastInvoiceNumber;
	}

	@Override
	public String toString() {
		return "MerchantProfile [id=" + id + ", login_id=" + login_id + ", name=" + name + ", legal_name=" + legal_name
				+ ", trading_address=" + trading_address + ", corporate_address=" + corporate_address
				+ ", trading_country=" + trading_country + ", coroporate_country=" + coroporate_country
				+ ", trading_state=" + trading_state + ", trading_postal=" + trading_postal + ", corporate_postal="
				+ corporate_postal + ", corporate_state=" + corporate_state + ", business_phone=" + business_phone
				+ ", contact_phone=" + contact_phone + ", contact_name=" + contact_name + ", reg_no=" + reg_no
				+ ", email_address=" + email_address + ", website_url=" + website_url + ", trading_start_date="
				+ trading_start_date + ", date_of_incorporate=" + date_of_incorporate + ", officer_title="
				+ officer_title + ", officer_name=" + officer_name + ", default_timezone=" + default_timezone
				+ ", default_curr=" + default_curr + ", is_sandbox=" + is_sandbox + ", isLiveTransAllowed="
				+ isLiveTransAllowed + ", isCardSeacrhAllowed=" + isCardSeacrhAllowed + ", agentId=" + agentId
				+ ", parentId=" + parentId + ", whiteId=" + whiteId + ", allowedApi=" + allowedApi + ", checkWhiteList="
				+ checkWhiteList + ", allowedPaymentMethod=" + allowedPaymentMethod + ", redirectType=" + redirectType
				+ ", qb_merchant_id=" + qb_merchant_id + ", qb_merchant_signupform=" + qb_merchant_signupform
				+ ", qb_merchant_id_status=" + qb_merchant_id_status + ", allowedTrustScore=" + allowedTrustScore
				+ ", checkBlackList=" + checkBlackList + ", applyConversionRiskFactor=" + applyConversionRiskFactor
				+ ", allowedCurr=" + allowedCurr + ", allowedDescriptor=" + allowedDescriptor
				+ ", secondToWaitPaymentExec=" + secondToWaitPaymentExec + ", cashier_template=" + cashier_template
				+ ", trxType=" + trxType + ", checkoutURL=" + checkoutURL + ", balanceURL=" + balanceURL
				+ ", isLivePersonAllowed=" + isLivePersonAllowed + ", profilePassword=" + profilePassword
				+ ", over_under_paid_allowed=" + over_under_paid_allowed + ", maximumRiskCapping=" + maximumRiskCapping
				+ ", conversionAllowed=" + conversionAllowed + ", conversionRiskFactor=" + conversionRiskFactor
				+ ", otherConversionRiskFactor=" + otherConversionRiskFactor + ", otherMaxAmount=" + otherMaxAmount
				+ ", threeds_allowed=" + threeds_allowed + ", network_token_allowed=" + network_token_allowed + "]";
	}

	public String getInteracEmail() {
		return interacEmail;
	}

	public void setInteracEmail(String interacEmail) {
		this.interacEmail = interacEmail;
	}

}
 