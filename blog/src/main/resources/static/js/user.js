let index={
	
 /*	 굳이 function 쓰고 싶으면 this바인딩하기
 let _this= this;
	init: function(){
		$("#btn-save").on("click", function(){ 
			this.save();
		});
	},
	*/
	
	init: function(){
		$("#btn-save").on("click",()=>{ // funtion(){}대신에 ()=>{}를 쓴 것은 this를 바인딩하기 위해
			this.save();
		});
		
	},
	
	save:function(){
		// alert("user의 save함수 호출");
		let data={
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		// console.log(data);
		
		/* ajax 호출 시 default가 비동기 호출
		ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청.
		ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환 */
		$.ajax({
			type: "POST",
			url:"/auth/joinProc",
			
			// data는 js라서 자바가 이해를 못하기 때문에 json으로 변환
			data: JSON.stringify(data), // http body data
			contentType: "application/json; charset= utf-8", // body data가 어떤 타입인지
			dataType: "json" // 요청을 서버로 해서 응답이 올 때 기본적으로 모든것이 문자열인데 생긴게 json이라면=> js objcet로 변경	
			
		}).done(function(resp){ // 회원가입 수행 요청 정상
			alert("회원가입이 완료 되었습니다.");
			// console.log(resp);
			location.href="/";
			
		}).fail(function(error){ // 회원가입 수행 요청 실패
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용하여 3개의 파라미터를 json으로 변경하여 insert요청
	},

/*
	login:function(){
		// alert("user의 save함수 호출");
		let data={
			username: $("#username").val(),
			password: $("#password").val()
		}

		$.ajax({
			type: "POST",
			url:"/api/user/login",
			
			// data는 js라서 자바가 이해를 못하기 때문에 json으로 변환
			data: JSON.stringify(data), // http body data
			contentType: "application/json; charset= utf-8", 
			dataType: "json"

		}).done(function(resp){ // 회원가입 수행 요청 정상
			alert("로그인이 완료 되었습니다.");
			location.href="/";
			
		}).fail(function(error){ // 회원가입 수행 요청 실패
			alert(JSON.stringify(error));
		}); 
	}
	*/
}
index.init();