package kr.green.springtest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.green.springtest.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(
	    HttpServletRequest request, 
	    HttpServletResponse response, 
	    Object handler, 
	    ModelAndView modelAndView)
	    throws Exception {
		    ModelMap modelMap = modelAndView.getModelMap();
		    // 컨트롤러에서 addObject로 보낼 때 이름이 user인 정보를 가져옴
		    // 컨트롤러에서 보내는 이름을  a로 수정한다면
		    // 여기에서도 get("a")로 수정해야함
		    UserVo user = (UserVo)modelMap.get("user");
	
		    if(user != null) {
		        HttpSession session = request.getSession();
		        // 세션에서 user로 저장하기 때문에
		        // jsp에서는 ${user}로 사용할 수 있음
		        // 여기서 이름을 b로 바꾸면
		        // jsp에서는 ${b}로 사용할 수 있음
		        session.setAttribute("user", user);
		    }
		    System.out.println(user);
		}
	
	
}
