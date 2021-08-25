package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDTO;

@ControllerAdvice // 모든 Exception발생 시 이 파일을 수행
@RestController
public class GlobalExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public ResponseDTO<String> handleArgumentException(Exception e) {
		return new ResponseDTO<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
	
	/*
	 * @ExceptionHandler(value = IllegalArgumentException.class) public String
	 * handleArgumentException(IllegalArgumentException e) {
	 * return "<h1>"+e.getMessage()+"</h1>"; }
	 */
	
	/* 모든 exception
	 * @ExceptionHandler(value = Exception.class) public String
	 * handleArgumentException(Exception e) { return "<h1>"+e.getMessage()+"</h1>";
	 * }
	 */
}
