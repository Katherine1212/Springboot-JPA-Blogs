let index={

	
	init: function(){
		$("#btn-save").on("click",()=>{ 
			this.save();
		});
		
		$("#btn-edit").on("click",()=>{ 
			this.edit();
		});
		
		$("#btn-delete").on("click",()=>{ 
			this.deleteById();
		});
		
	},
	
	save:function(){
		let data={
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		$.ajax({
			type: "POST",
			url:"/api/board",
			
			data: JSON.stringify(data),
			contentType: "application/json; charset= utf-8", 
			dataType: "json" 
			
		}).done(function(resp){ 
			alert("글 등록이 완료되었습니다.");
			location.href="/";
			
		}).fail(function(error){ 
			alert(JSON.stringify(error));
		}); 
	},
	
	edit:function(){
		let data={
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		$.ajax({
			type: "POST",
			url:"/api/board",
			
			data: JSON.stringify(data),
			contentType: "application/json; charset= utf-8", 
			dataType: "json" 
			
		}).done(function(resp){ 
			alert("글 등록이 완료되었습니다.");
			location.href="/";
			
		}).fail(function(error){ 
			alert(JSON.stringify(error));
		}); 
	},
	
	deleteById:function(){
		var boardId= $("#boardId").text();
		
		$.ajax({
			type: "DELETE",
			url:"/api/board/"+boardId,
			dataType: "json" 
			
		}).done(function(resp){ 
			alert("삭제가 완료되었습니다.");
			location.href="/";
			
		}).fail(function(error){ 
			alert(JSON.stringify(error));
		}); 
	},

}
index.init();