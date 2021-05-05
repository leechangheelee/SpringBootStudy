package com.board.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
import com.board.domain.BoardVO;
import com.board.mapper.BoardMapper;
 
@Controller
@RequestMapping("/board")
public class BoardController {
 
    @Autowired
    private BoardMapper boardMapper;
    
    
    //게시글 목록
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView list() throws Exception{
        
        List<BoardVO> list = boardMapper.boardList();
        
        return new ModelAndView("boardList","list",list);
    }
    
    //게시글 작성 페이지(GET)    
    @RequestMapping(value="/post",method=RequestMethod.GET)
    public ModelAndView writeForm() throws Exception{
        
        return new ModelAndView("boardWrite");
    }
    
    //게시글 작성(POST)
    @RequestMapping(value="/post",method=RequestMethod.POST)
    public String write(@ModelAttribute("BoardVO") BoardVO board) throws Exception{
 
        boardMapper.boardInsert(board);
        
        return "redirect:/board";
    }
    
    //게시글 상세
    @RequestMapping(value="/{bno}",method=RequestMethod.GET)
    public ModelAndView view(@PathVariable("bno") int bno) throws Exception{

        BoardVO board = boardMapper.boardView(bno);
        boardMapper.hitPlus(bno);

        return new ModelAndView("boardView","board",board);
    }
    
    //게시글 수정 페이지(GET)
    @RequestMapping(value="/post/{bno}", method=RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("bno") int bno) throws Exception{

        BoardVO board = boardMapper.boardView(bno);

        return new ModelAndView("boardUpdate","board",board);
    }

    //게시글 수정(PATCH)
    @RequestMapping(value="/post/{bno}", method=RequestMethod.PATCH)
    public String update(@ModelAttribute("BoardVO")BoardVO board,@PathVariable("bno") int bno) throws Exception{

        boardMapper.boardUpdate(board);

        return "redirect:/board/"+bno;
    }
    //게시글 삭제(DELETE)
    @RequestMapping(value="/post/{bno}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("bno") int bno) throws Exception{

        boardMapper.boardDelete(bno);

        return "redirect:/board";
    }
}