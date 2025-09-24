package paysecure.common.db.mysql.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import paysecure.common.db.mysql.model.pgs.MerchantProfile;

public class MerchantProfileMapper implements RowMapper<MerchantProfile> {

	private long id;
	private Long login_id;
	private String name;
	private String legal_name;
	private String trading_address;
	private String corporate_address;
	private String trading_country;
	private String coroporate_country;
	private String trading_state;
	private String trading_postal;
	private String corporate_postal;
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
	private int filled;
	private String profilePassword = "";
	private int maximumRiskCapping;
	private int conversionAllowed;
	private float conversionRiskFactor;
	private float otherConversionRiskFactor;
	private float otherMaxAmount;
	private float sameConversionRiskFactor;
	private int sameMaximumRiskCapping;
	private Boolean check_skip_risk_factor;

	public MerchantProfile mapRow(ResultSet resultSet, int i) throws SQLException {

		MerchantProfile MP = new MerchantProfile();
		MP.setId(resultSet.getLong("id"));
		MP.setLogin_id(resultSet.getInt("login_id"));
		MP.setName(resultSet.getString("name"));
		MP.setLegal_name(resultSet.getString("legal_name"));
		MP.setTrading_address(resultSet.getString("trading_address"));
		MP.setCorporate_address(resultSet.getString("corporate_address"));
		MP.setTrading_country(resultSet.getString("trading_country"));
		MP.setCoroporate_country(resultSet.getString("coroporate_country"));
		MP.setTrading_state(resultSet.getString("trading_state"));
		MP.setCorporate_state(resultSet.getString("corporate_state"));

		MP.setTrading_postal(resultSet.getString("trading_postal"));
		MP.setCorporate_postal(resultSet.getString("corporate_postal"));
		MP.setBusiness_phone(resultSet.getString("business_phone"));
		MP.setContact_phone(resultSet.getString("contact_phone"));
		MP.setContact_name(resultSet.getString("contact_name"));
		MP.setReg_no(resultSet.getString("reg_no"));
		MP.setEmail_address(resultSet.getString("email_address"));
		MP.setWebsite_url(resultSet.getString("website_url"));
		MP.setTrading_start_date(resultSet.getString("trading_start_date"));
		MP.setDate_of_incorporate(resultSet.getString("date_of_incorporate"));
		MP.setOfficer_title(resultSet.getString("officer_title"));
		MP.setOfficer_name(resultSet.getString("officer_name"));

		MP.setDefault_curr(resultSet.getString("default_curr"));
		MP.setDefault_timezone(resultSet.getString("default_timezone"));
		MP.setIs_sandbox(resultSet.getInt("is_sandbox"));
		MP.setIsLiveTransAllowed(resultSet.getInt("isLiveTransAllowed"));

		
		MP.setAgentId(resultSet.getInt("agentId"));
		MP.setParentId(resultSet.getInt("parentId"));
		MP.setWhiteId(resultSet.getInt("WhiteId"));
		MP.setAllowedApi(resultSet.getString("allowedApi"));
		MP.setCheckWhiteList(resultSet.getInt("checkWhiteList"));
		MP.setAllowedPaymentMethod(resultSet.getString("allowedPaymentMethod"));
		MP.setRedirectType(resultSet.getString("redirectType"));

		MP.setQb_merchant_id(resultSet.getString("qb_merchant_id"));
		MP.setQb_merchant_signupform(resultSet.getString("qb_merchant_signupform"));
		MP.setQb_merchant_id_status(resultSet.getString("qb_merchant_id_status"));

		MP.setAllowedTrustScore(resultSet.getInt("allowedTrustScore"));
		MP.setCheckBlackList(resultSet.getInt("checkBlackList"));
		MP.setApplyConversionRiskFactor(resultSet.getInt("applyConversionRiskFactor"));
		MP.setAllowedCurr(resultSet.getString("allowedCurr"));
		MP.setAllowedDescriptor(resultSet.getString("allowedDescriptor"));
		MP.setSecondToWaitPaymentExec(resultSet.getInt("secondToWaitPaymentExec"));
		MP.setTrxType(resultSet.getString("trxType"));
		MP.setCashier_template(resultSet.getString("cashier_template"));
		MP.setCheckoutURL(resultSet.getString("checkoutURL"));
		MP.setBalanceURL(resultSet.getString("cust_bal_url"));
		MP.setIsLivePersonAllowed(resultSet.getInt("isLivePersonAllowed"));

		MP.setIsCardSeacrhAllowed(resultSet.getInt("isCardSeacrhAllowed"));
		// MP.setFilled(resultSet.getInt("filled"));

		MP.setProfilePassword(resultSet.getString("profilePassword"));
		MP.setOver_under_paid_allowed(resultSet.getInt("over_under_paid_allowed"));
		MP.setMaximumRiskCapping(resultSet.getInt("maximumRiskCapping"));
		MP.setConversionAllowed(resultSet.getInt("conversionAllowed"));
		MP.setConversionRiskFactor(resultSet.getFloat("conversionRiskFactor"));
		MP.setOtherConversionRiskFactor(resultSet.getFloat("otherConversionRiskFactor"));
		MP.setOtherMaxAmount(resultSet.getFloat("otherMaxAmount"));
		MP.setThreeds_allowed(resultSet.getInt("threeds_allowed"));
		MP.setNetwork_token_allowed(resultSet.getInt("network_token_allowed"));
		MP.setSameConversionRiskFactor(resultSet.getFloat("sameConversionRiskFactor"));
		MP.setSameMaximumRiskCapping(resultSet.getInt("sameMaximumRiskCapping"));
		MP.setCheck_skip_risk_factor(resultSet.getBoolean("check_skip_risk_factor"));
		MP.setLastInvoiceNumber(resultSet.getInt("last_invoice"));
		MP.setInteracEmail(resultSet.getString("interacEmail"));
		
		return MP;
	}
}
 