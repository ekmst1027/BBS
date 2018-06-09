package kr.ac.dbnis.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.ac.dbnis.common.JDBCUtil;
import kr.ac.dbnis.user.UserVO;

// DAO(Data Access Object)
@Repository("userDAO")
public class UserDAO{
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// SQL 명렁어들
	private final String USER_GET = "SELECT * FROM users WHERE USERID=? AND PASSWD=?";
	private final String USER_GETLIST = "SELECT * FROM users";
	private final String GET_USERLIST = "SELECT userid FROM users";
	private final String CREATE_USER = "INSERT INTO users(USERID, PASSWD) values(?,?)";
	
	// CRUD 기능의 메소드 구현
	// 회원 정보 가져오기
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getUserid());
			stmt.setString(2, vo.getPasswd());
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				user = new UserVO();
				user.setUserid(rs.getString("USERID"));
				user.setPasswd(rs.getString("PASSWD"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}
	
	// 회원 정보 리스트 가져오기
	public List<UserVO> getUserList(UserVO vo) {
		List<UserVO> userList = new ArrayList<UserVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GETLIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				UserVO user = new UserVO();
				user.setUserid(rs.getString("USERID"));
				user.setPasswd(rs.getString("PASSWD"));
				userList.add(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return userList;
	}
	
	// 회원 아이디 리스트 가져오기
	public List<String> getUserIdList(UserVO vo) {
		List<String> userList = new ArrayList<String>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(GET_USERLIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				UserVO user = new UserVO();
				user.setUserid(rs.getString("USERID"));
				String users = String.valueOf(user.getUserid());
				userList.add(users);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return userList;
	}
	
	// 회원 정보 삽입하기
	public void insertUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CREATE_USER);
			stmt.setString(1, vo.getUserid());
			stmt.setString(2, vo.getPasswd());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
}	// finish UserDAO class

