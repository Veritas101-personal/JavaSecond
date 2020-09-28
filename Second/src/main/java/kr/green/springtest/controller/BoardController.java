package kr.green.springtest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.green.springtest.pagenation.Criteria;
import kr.green.springtest.pagenation.PageMaker;
import kr.green.springtest.service.BoardService;
import kr.green.springtest.service.UserService;
import kr.green.springtest.utils.UploadFileUtils;
import kr.green.springtest.vo.BoardVo;
import kr.green.springtest.vo.UserVo;


/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	
	private String uploadPath = "D:\\jk\\git\\Second_files";
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value= "/board", method = RequestMethod.GET)
	public ModelAndView boardList(ModelAndView mv, Criteria cri){
	    logger.info("URI:/board");
	    mv.setViewName("/board/board");
	    PageMaker pm = boardService.getPageMakerByBoard(cri);
	    ArrayList<BoardVo> list = boardService.getBoardList(cri);
	    mv.addObject("list", list);
	    mv.addObject("pm",pm);
	    return mv;
	}

	@RequestMapping(value= "/board/content", method = RequestMethod.GET)
	public ModelAndView increaseWatchNum(ModelAndView mv, Integer num) {
		mv.setViewName("/board/content");
		BoardVo board = null;
		
		if (num != null) {
			board = boardService.getNum(num);
			mv.addObject("board", board);
		} if (board != null) {
			boardService.increaseNum(num);
			board.setWatchNum(board.getWatchNum()+1);
		}
		return mv;
	}
	@RequestMapping(value= "/board/write", method = RequestMethod.GET)
	public ModelAndView addArticleGet(ModelAndView mv, HttpServletRequest request) {
		logger.info("URI:/board/write:GET");
		HttpSession session = request.getSession();
		if (session == null) {
			mv.setViewName("redirect:/");
		} else {
			mv.setViewName("/board/write");
		}
		return mv;
	}
	@RequestMapping(value= "/board/write", method = RequestMethod.POST)
	public ModelAndView addArticlePost(ModelAndView mv, BoardVo board, HttpServletRequest request, MultipartFile file2) throws Exception {
		logger.info("URI:/board/write:POST");
		HttpSession session = request.getSession();
		if (session == null) {
			mv.setViewName("redirect:/");
		} else {
			String fileName = UploadFileUtils.uploadFile(uploadPath, file2.getOriginalFilename(),file2.getBytes());
			board.setFile(fileName);
			mv.setViewName("redirect:/board");
			boardService.addArticle(board,request);
		}
		return mv;
	}

	@RequestMapping(value= "/board/modify", method = RequestMethod.GET)
	public ModelAndView modifyContentGet(ModelAndView mv, Integer num, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute("user");
		if (user == null) {
			mv.setViewName("redirect:/");
		} else  {
			mv.setViewName("/board/modify");
		}
		BoardVo board = null;
		if(num != null) {
			board = boardService.getNum(num);
			
		}
		mv.addObject("board",board);
		return mv;
	}
	@RequestMapping(value= "/board/modify", method = RequestMethod.POST)
	public ModelAndView modifyContentPost(ModelAndView mv, BoardVo board, HttpServletRequest request, MultipartFile file2) throws Exception {
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute("user");
		board.setWriter(userService.getUser(request).getId());
		// 새로운 첨부 파일이 추가가 되면
		if (user == null) {
			mv.setViewName("redirect:/");
		} else {
			if (!file2.getOriginalFilename().equals("")) {
				String fileName = UploadFileUtils.uploadFile(uploadPath, file2.getOriginalFilename(),file2.getBytes());
				board.setFile(fileName);
			} else if (board.getFile() == null || board.getFile().equals("")) {
				board.setFile(null);
			}
			boardService.modifyContent(board);
			mv.setViewName("redirect:/board");
		}
		
		return mv;
	}

	@RequestMapping(value= "/board/delete", method = RequestMethod.GET)
	public ModelAndView deleteContent(ModelAndView mv, BoardVo board, HttpServletRequest request) {
		logger.info("URI:/board/delete");
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute("user");
		if (user == null) {
			mv.setViewName("redirect:/");
		} else {
			mv.setViewName("redirect:/board");
			boardService.deleteContent(board);
		}
		return mv;
	}
	
	@RequestMapping(value= "/board/up", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object,Object> boardUp(@RequestBody int num, HttpServletRequest request){
	    Map<Object,Object> map = new HashMap<Object,Object>();
	    UserVo user = userService.getUser(request);
	    if(user == null) {
	    	map.put("isUser",false);
	    } else {
	    	map.put("isUser",true);
	    	int up = boardService.updateUp(num,user.getId());
	    	map.put("up", up);
	    }
	    return map;
	}
	
	
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String boardRegisterPost(BoardVo board, HttpServletRequest request, MultipartFile file2) throws Exception {
		/* ... 이전 코드 생략 */

		uploadFile(file2.getOriginalFilename(),file2.getBytes());
		return "redirect:/board/";
	}
	
	/* 서버에 저장 */
	private String uploadFile(String name, byte[] data)
		throws Exception{
	    /* 고유한 파일명을 위해 UUID를 이용 */
		UUID uid = UUID.randomUUID();
		String savaName = uid.toString() + "_" + name;
		File target = new File(uploadPath, savaName);
		FileCopyUtils.copy(data, target);
		return savaName;
	
	}
	
	@ResponseBody
	@RequestMapping(value="/board/download")
	public ResponseEntity<byte[]> downloadFile(String fileName)throws Exception{
	    InputStream in = null;
	    ResponseEntity<byte[]> entity = null;
	    try{
	    	// HttpHeader 객체 생성
	    	HttpHeaders headers = new HttpHeaders();
	    	// 다운로드 할 파일을 읽어옴
	        fileName = fileName.substring(fileName.lastIndexOf(".")+1);
	        // 다운로드 시 저장할 파일명
	        in = new FileInputStream(uploadPath+fileName);
	        // 해더에 컨텐츠 타입을 설정
	        fileName = fileName.substring(fileName.indexOf("_")+1);
	        // 헤더 정보를 추가
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.add("Content-Disposition",  "attachment; filename=\"" 
				+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
	        // ResponseEntity 객체 생성, 전송할 파일, 헤더 정보, 헤더 상태
	        entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
	    }catch(Exception e) {
	        e.printStackTrace();
	        entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	    }finally {
	        in.close();
	    }
	    return entity;
	}
	
}

