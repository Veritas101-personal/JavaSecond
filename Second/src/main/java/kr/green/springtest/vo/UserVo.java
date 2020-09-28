package kr.green.springtest.vo;

public class UserVo {
	
	private String id;
	private String password;
	private String email;
	private String gender;
	private String auth;
	private String isDel;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", password=" + password + ", email=" + email + ", gender=" + gender + ", auth="
				+ auth + ", isDel=" + isDel + "]";
	}
	
	
	
}

