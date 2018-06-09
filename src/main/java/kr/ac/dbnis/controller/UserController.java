package kr.ac.dbnis.controller;

import java.util.List;

import kr.ac.dbnis.user.UserVO;
import kr.ac.dbnis.user.impl.UserDAO;

public class UserController {
	boolean judgeSameID(UserVO vo, String newID) {
		UserDAO userDAO = new UserDAO();
		List<String> userList =  userDAO.getUserIdList(vo);
		
		for (String id : userList) {
			if(id.equals(newID)) {
				return true;
			}
		}
		return false;
	}
	
	void createAccount(UserVO vo) {
		UserDAO userDAO = new UserDAO();
		userDAO.insertUser(vo);
	}
	
	boolean judgeLogin(UserVO vo) {
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		if(user != null) {
			return true;			
		}else {
			return false;
		}
	}
	
	
	
}
