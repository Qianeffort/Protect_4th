package project.tarena.entity;

public class ManagerUser {
	private	int	id	;//管理员ID
	private	String	realName	;//真实姓名
	private	String	managerName	;//管理员帐号
	private	String	passWord	;//密码
	private	String	phoneNum	;//电话号码
	private	String	email	;//电子邮箱
	private	String	role	;//角色
	private	String	authorization_date	;//授权日期
	private String lastlogintime;//最近一次的登录时间
//	private String logintime;//当前登录时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAuthorization_date() {
		return authorization_date;
	}
	public void setAuthorization_date(String authorization_date) {
		this.authorization_date = authorization_date;
	}
	public String getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
//	public String getLogintime() {
//		return logintime;
//	}
//	public void setLogintime(String logintime) {
//		this.logintime = logintime;
//	}


}
