package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

// 스프링이 컴포넌트 스캔 통해서 Bean에 등록(IoC를 해줌)
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void write(Board board, User user) { // title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> list(Pageable pageable) {
		
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board detail(int boardId) {
		return boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패");
		});
	}

	@Transactional
	public void delete(int boardId) {
		System.out.println("//////////////////////"+boardId);
		boardRepository.deleteById(boardId);
	}
}
