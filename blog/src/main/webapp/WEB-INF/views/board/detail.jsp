<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">목록</button>
	<c:if test="${board.user.user_id == principal.user.user_id}">
		<button id="btn-edit" class="btn btn-secondary">수정</button>
		<button id="btn-delete" class="btn btn-secondary">삭제</button>
	</c:if>
	<br /> <br />
	<div>
		글 번호: <span id="boardId"><i>${board.boardId}</i></span> 작성자 : <span><i>${board.user.username}</i></span>
	</div>
	<br />
	<div>
		<h3>${board.title}</h3>
	</div>
	<hr />
	<div>
		<div>${board.content}</div>
	</div>
	<hr />
</div>

<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>

