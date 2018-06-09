package kr.ac.dbnis.user;

import java.util.List;

public class UserVO {
	private String userid;
	private String passwd;
	private List<String> userIdList;
	
	// declare Getters and Setters
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public List<String> getUserIdList() {
		return userIdList;
	}
	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}
	// declare toString() method
	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", passwd=" + passwd + "]";
	}
	
}	// finish UserVO class()
