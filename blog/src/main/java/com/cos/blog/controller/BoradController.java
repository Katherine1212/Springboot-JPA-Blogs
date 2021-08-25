package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.service.BoardService;

@Controller
public class BoradController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/"})
	public String index(Model model, @PageableDefault(size = 5, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable) { 
		model.addAttribute("boards", boardService.list(pageable));
		// WEB-INF/views/index.jsp
		return "index";
	}
	@GetMapping("/board/{boardId}")
	public String findById(@PathVariable int boardId, Model model) {
		model.addAttribute("board", boardService.detail(boardId));
		return "board/detail";
	}
	
	// USER권한 필요
	@GetMapping({"/board/saveForm"})
	public String saveForm() { 
		
		return "board/saveForm";
	}
}
