package paysecure.common.db.mysql.model.pgs;

public class AuthLogin {
	private long id;
	private String name;
	private int logintype;
	private String emailaddress;
	private String ipaddress;
	private String sessionid;
	private String logintime;
	private String createtime;
	private String password;
	private String browserinfo;
	private String typename;
	private int parentId = -1;
	private int agentId = -1;
	private int whiteId = -1;
	private int is_force_change = 0;
	private int is_two_factor_setup = 0;
	private int disable_two_factor_setup = 0;
	private String merchantName;
	private int parentAuthid = 0; // add same user by merchant/whitelabel/agent it store the id of user who
									// created this

	private int new_mail_sent = 0;
	private int reset_mail_sent = 0;
	private int email_verified;
	private String verification_hash;
	private int bankId = -1;

	private String preferred_language;

	private String allowed_theme;
	private Boolean allowNewDashboard;

	

	public AuthLogin() {
	}

	public AuthLogin(Long id, String name, int logintype, String emailaddress, String ipaddress, String sessionid,
			String logintime, String createtime, String password, String browserinfo, int parentId, int agentId) {
		this.id = id;
		this.name = name;
		this.logintype = logintype;
		this.emailaddress = emailaddress;
		this.ipaddress = ipaddress;
		this.sessionid = sessionid;
		this.logintime = logintime;
		this.createtime = createtime;
		this.password = password;
		this.browserinfo = browserinfo;
		this.parentId = parentId;
		this.agentId = agentId;

	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrowserinfo() {
		return browserinfo;
	}

	public void setBrowserinfo(String browserinfo) {
		this.browserinfo = browserinfo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLogintype() {
		return logintype;
	}

	public void setLogintype(int logintype) {
		this.logintype = logintype;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getEmail_verified() {
		return email_verified;
	}

	public void setEmail_verified(int email_verified) {
		this.email_verified = email_verified;
	}

	@Override
	public String toString() {
		return "AuthLogin [id=" + id + ", name=" + name + ", logintype=" + logintype + ", emailaddress=" + emailaddress
				+ ", merchantName=" + merchantName + "]";
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getWhiteId() {
		return whiteId;
	}

	public void setWhiteId(int whiteId) {
		this.whiteId = whiteId;
	}

	public int getIs_force_change() {
		return is_force_change;
	}

	public void setIs_force_change(int is_force_change) {
		this.is_force_change = is_force_change;
	}

	public int getIs_two_factor_setup() {
		return is_two_factor_setup;
	}

	public void setIs_two_factor_setup(int is_two_factor_setup) {
		this.is_two_factor_setup = is_two_factor_setup;
	}

	public int getDisable_two_factor_setup() {
		return disable_two_factor_setup;
	}

	public void setDisable_two_factor_setup(int disable_two_factor_setup) {
		this.disable_two_factor_setup = disable_two_factor_setup;
	}

	public int getParentAuthid() {
		return parentAuthid;
	}

	public void setParentAuthid(int parentAuthid) {
		this.parentAuthid = parentAuthid;
	}

	public int getNew_mail_sent() {
		return new_mail_sent;
	}

	public void setNew_mail_sent(int new_mail_sent) {
		this.new_mail_sent = new_mail_sent;
	}

	public int getReset_mail_sent() {
		return reset_mail_sent;
	}

	public void setReset_mail_sent(int reset_mail_sent) {
		this.reset_mail_sent = reset_mail_sent;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getVerification_hash() {
		return verification_hash;
	}

	public void setVerification_hash(String verification_hash) {
		this.verification_hash = verification_hash;
	}

	public String getPreferred_language() {
		return preferred_language;
	}

	public void setPreferred_language(String preferred_language) {
		this.preferred_language = preferred_language;
	}

	public String getAllowed_theme() {
		return allowed_theme;
	}

	public void setAllowed_theme(String allowed_theme) {
		this.allowed_theme = allowed_theme;
	}


	public Boolean getAllowNewDashboard() {
		return allowNewDashboard;
	}

	public void setAllowNewDashboard(Boolean allowNewDashboard) {
		this.allowNewDashboard = allowNewDashboard;
	}

}
