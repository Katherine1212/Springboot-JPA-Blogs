package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipelDetail;
import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;


@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDTO<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipelDetail principal) {
		boardService.write(board, principal.getUser());
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{boardId}")
	public ResponseDTO<Integer> deleteById(@PathVariable int boardId){
		boardService.delete(boardId);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
}
