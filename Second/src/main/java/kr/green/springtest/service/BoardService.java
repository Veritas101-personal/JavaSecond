package kr.green.springtest.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import kr.green.springtest.pagenation.Criteria;
import kr.green.springtest.pagenation.PageMaker;
import kr.green.springtest.vo.BoardVo;

public interface BoardService {

	ArrayList<BoardVo> getBoardList(Criteria cri);
	
	BoardVo getNum(Integer num);

	void increaseNum(Integer num);

	void addArticle(BoardVo board, HttpServletRequest request);

	void modifyContent(BoardVo board);

	void deleteContent(BoardVo board);

	PageMaker getPageMakerByBoard(Criteria cri);

	int updateUp(int num, String id);
}
