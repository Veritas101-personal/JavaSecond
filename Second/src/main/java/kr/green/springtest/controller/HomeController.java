package kr.green.springtest.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.green.springtest.service.UserService;
import kr.green.springtest.vo.UserVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value= "/", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView home(ModelAndView mv, UserVo inputUser){
	    logger.info("URI:/");
	    mv.setViewName("/main/home");
	    UserVo user = userService.isUser(inputUser);
	    mv.addObject("id",inputUser.getId());
	    if(user == null) {
	    	mv.addObject("isLogin", false);
	    } else {
	    	mv.addObject("user", user);
	    }

	    return mv;
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public ModelAndView registerGet(ModelAndView mv) {
		mv.setViewName("/member/register");
		
		return mv;
		
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ModelAndView registerPost(ModelAndView mv, UserVo user) {
	
		if (userService.registerUser(user))
			mv.setViewName("/main/home");
		else {
			mv.setViewName("redirect:/signup");
		}
		
	return mv;
		
	}
	
	@RequestMapping(value= "/signout", method = RequestMethod.GET)
	public ModelAndView logoutGet(ModelAndView mv, HttpServletRequest request){
	    logger.info("URI:/");
	    mv.setViewName("/main/home");
	    request.getSession().removeAttribute("user");

	    return mv;
	}

	@RequestMapping(value= "/idCheck")
	@ResponseBody
	public Map<Object,Object> idCheck(@RequestBody String id){
	    
		Map<Object,Object> map = new HashMap<Object,Object>();
	    map.put("check", userService.getUser(id)==null);
	    return map;
	}
}
