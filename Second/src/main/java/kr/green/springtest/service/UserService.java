package kr.green.springtest.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import kr.green.springtest.vo.UserVo;

public interface UserService {
	
	@Autowired
	UserVo getUser(HttpServletRequest request);

	UserVo isUser(UserVo id);

	boolean registerUser(UserVo user);

	UserVo getUser(String id);


}
