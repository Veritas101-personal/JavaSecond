package kr.green.springtest.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.springtest.dao.UserDao;
import kr.green.springtest.vo.UserVo;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserVo getUser(HttpServletRequest request) {
		return (UserVo)request.getSession().getAttribute("user");
	}

	@Override
	public UserVo isUser(UserVo inputUser) {
		// 일반적으로 로그인 과정은 DB에서 아이디와 일치하는 정보를 가져와서
		// 입력받은 아이디와 가져온 정보 중 비밀번호를 비교하여 로그인을 결정한다.
		// => 쿼리로 비밀번호를 비교하지 않는다
		// => 입력한 비밀번호는 실제 비밀번호이고  db에 저장된 비밀번호는 암호화된 비밀번호이기 때문에 쿼리로 직접 비교할 수 없다.
		// => 다른 이유로는  password에 이상한 작업을 하면 로그인이 될 수 있기 때문에 (Blind SQL Injection)
		UserVo user = userDao.getUser(inputUser.getId());
		if (user != null && passwordEncoder.matches(inputUser.getPassword(), user.getPassword())) {
			return user;
		} else {
			return null;
		}
		
		
	}


	@Override
	public boolean registerUser(UserVo user) {
		if (user == null)
			return false;
		if(userDao.getUser(user.getId()) != null || user.getId().length() ==0)
			return false;
		if(user.getPassword()==null || user.getPassword().length() == 0)
			return false;
		if(user.getEmail()==null || user.getEmail().length() == 0 || !user.getEmail().contains("@"))
			return false;
		if(user.getGender()==null || user.getGender().length() == 0)
			user.setGender("Male");
		user.setAuth("USER");
		user.setIsDel("N");
	
		
		String encodePw = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePw);
		
		userDao.registerUser(user);
		
		return true;
		
	}

	@Override
	public UserVo getUser(String id) {
		return userDao.getUser(id);
	}
	
	
}
