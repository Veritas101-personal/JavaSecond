package kr.green.springtest.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.springtest.dao.BoardDao;
import kr.green.springtest.pagenation.Criteria;
import kr.green.springtest.pagenation.PageMaker;
import kr.green.springtest.vo.BoardVo;
import kr.green.springtest.vo.UserVo;

@Service
public class BoardServiceImp implements BoardService{

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public ArrayList<BoardVo> getBoardList(Criteria cri) {
		// TODO Auto-generated method stub
		return boardDao.getBoardList(cri);
	}

	@Override
	public BoardVo getNum(Integer num) {
		// TODO Auto-generated method stub
		return boardDao.getNum(num);
	}

	@Override
	public void increaseNum(Integer num) {
		boardDao.increaseNum(num);
		
		
	}

	@Override
	public void addArticle(BoardVo board, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute("user");
		if(user == null)	return ;
		board.setWriter(user.getId());
		boardDao.addArticle(board);
		
	}

	@Override
	public void modifyContent(BoardVo board) {
		boardDao.modifyContent(board);
		
	}

	@Override
	public void deleteContent(BoardVo board) {
		boardDao.deleteContent(board);
		
	}

	@Override
	public PageMaker getPageMakerByBoard(Criteria cri) {
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(boardDao.getTotalCountByBoard(cri));
		return pm;
	}

	@Override
	public int updateUp(int num, String id) {
		//추천테이블에서 게시글 번호와 아이디와 일치하는 값이 있는 검색해서
		// 이미 추천했다면 -1을 리턴
		if(boardDao.selectUp(num,id) != 0)
			return -1;
		// 추천 테이블에 추천을 등록
		boardDao.insertUp(num,id);
		// 게시글 정보를 가져온 후 업데이트함
		boardDao.updateBoardByUp(num);
		// 게시글 정보를 가져옴
		BoardVo board = boardDao.getBoard(num);
		return board.getUp();
		
	}

}
