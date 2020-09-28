package kr.green.springtest.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.green.springtest.pagenation.Criteria;
import kr.green.springtest.vo.BoardVo;

public interface BoardDao {

	ArrayList<BoardVo> getBoardList(@Param("cri")Criteria cri);

	BoardVo getNum(@Param("num")Integer num);

	void increaseNum(@Param("num")Integer num);

	void addArticle(@Param("board")BoardVo board);

	void modifyContent(@Param("board")BoardVo board);

	void deleteContent(@Param("board")BoardVo board);

	int getTotalCountByBoard(@Param("cri")Criteria cri);

	int selectUp(@Param("boNum")int num, @Param("id")String id);

	void insertUp(@Param("boNum")int num, @Param("id")String id);

	void updateBoardByUp(@Param("num")int num);

	BoardVo getBoard(@Param("num")Integer num);

	


}
