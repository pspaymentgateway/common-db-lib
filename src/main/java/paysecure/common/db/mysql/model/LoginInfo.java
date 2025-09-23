
package paysecure.common.db.mysql.model;

public class LoginInfo {
	private int parentId;
    private int agentId;
    private int whiteId;
    private String userName;
    private int haveParent;
    private String roleType;
    private AuthLogin authLogin;
	private int userId;

	@Override
    public String toString() {
        return "UserLoginInfo [parentId=" + parentId + ", agentId=" + agentId
               + ", whiteId=" + whiteId + ", userName=" + userName + ", haveParent=" + haveParent + ", roleType="
               + roleType + ", authlogin=" + authLogin + "]";
    }

	public LoginInfo(int parentId, int agentId, int whiteId, String userName,
                         int haveParent, String roleType, AuthLogin authLogin) {
        this.parentId = parentId;
        this.agentId = agentId;
        this.whiteId = whiteId;
        this.userName = userName;
        this.haveParent = haveParent;
        this.roleType = roleType;
        this.authLogin = authLogin;
    }

	 public LoginInfo() {
       this.parentId = -1;
       this.agentId = 0;
       this.whiteId = 0;
       this.userName = null;
       this.haveParent = 0;
       this.roleType = null;
       this.authLogin = null;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getHaveParent() {
		return haveParent;
	}
	public void setHaveParent(int haveParent) {
		this.haveParent = haveParent;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public AuthLogin getAuthLogin() {
		return authLogin;
	}
	public void setAuthLogin(AuthLogin authLogin) {
		this.authLogin = authLogin;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	


}
