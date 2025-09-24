package paysecure.common.db.mysql.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import paysecure.common.db.mysql.model.AuthLogin;

public class LoginMapper implements RowMapper<AuthLogin> {

	private long id;
	private String name;
	private int logintype;
	private String emailaddress;
	private String ipaddress;
	private String sessionid;
	private String logintime;
	private String createtime;
	private String preferred_language;
	private Boolean allowNewDashboard;

	public AuthLogin mapRow(ResultSet resultSet, int i) throws SQLException {

		AuthLogin authlogin = new AuthLogin();
		authlogin.setId(resultSet.getLong("login_id"));
		authlogin.setEmailaddress(resultSet.getString("email_address"));
		authlogin.setIpaddress(resultSet.getString("last_ip"));
		authlogin.setSessionid(resultSet.getString("last_session_id"));
		authlogin.setLogintype(resultSet.getInt("login_type"));
		authlogin.setTypename(resultSet.getString("type_name"));
		authlogin.setName(resultSet.getString("login_name"));
		authlogin.setCreatetime(resultSet.getString("create_time"));
		authlogin.setPassword(resultSet.getString("password_md5"));//

		authlogin.setAgentId(resultSet.getInt("agentId"));
		authlogin.setWhiteId(resultSet.getInt("whiteId"));
		authlogin.setParentId(resultSet.getInt("parentId"));
		authlogin.setIs_force_change(resultSet.getInt("is_force_change"));
		authlogin.setIs_two_factor_setup(resultSet.getInt("is_two_factor_setup"));
		authlogin.setDisable_two_factor_setup(resultSet.getInt("disable_two_factor_setup"));
		authlogin.setParentAuthid(resultSet.getInt("parentAuthid"));

		authlogin.setNew_mail_sent(resultSet.getInt("new_mail_sent"));
		authlogin.setReset_mail_sent(resultSet.getInt("reset_mail_sent"));
		authlogin.setBankId(resultSet.getInt("bankId"));
		authlogin.setEmail_verified(resultSet.getInt("email_verified"));
		authlogin.setVerification_hash(resultSet.getString("verification_hash"));

		authlogin.setPreferred_language(resultSet.getString("preferred_language"));

		authlogin.setAllowed_theme(resultSet.getString("allowed_theme"));

		authlogin.setAllowNewDashboard(resultSet.getBoolean("allow_new_dashboard"));


		return authlogin;
	}
}
