package com.board.mapper;
 
 
import java.util.List;
 
import com.board.domain.BoardVO;
 
public interface BoardMapper {
 
    //글작성
    public void boardInsert(BoardVO vo)throws Exception;
    
    //글목록
    public List<BoardVO> boardList()throws Exception;
    
    //글보기
    public BoardVO boardView(int bno)throws Exception;
    
    //조회수 증가
    public void hitPlus(int bno)throws Exception;
    
    //글수정
    public void boardUpdate(BoardVO vo)throws Exception;
    
    //글삭제
    public void boardDelete(int bno)throws Exception;
    
}