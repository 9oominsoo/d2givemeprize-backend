<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>tag(search) page</title>	
	
	<!--  autocomplete -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/jquery/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/bootstrap.min.js"></script>
</head>

<body>
	<div class="sharePost">
		<input type="text" id="postno" placeholder="게시글 넘버">
		<button id="sharePost">공유</button>
	</div>
		
	<hr/>
	
	<div class="searchFunction">
		<input id="searchbox" type="text" placeholder="유저 검색">
		<button id="searchbtn">검색</button>
	</div>
	
	<hr/>
	
	<div class="replyinfo">
		<input type="text" id="rpostno" placeholder="게시글 넘버">
		<input type="text" id="replyContent" placeholder="댓글 내용">
		<button id="insertReply">댓글만 달기</button>
		<button id="insertReplyTag">댓글 달고 공유하기</button>
	</div>
	<hr/>
	<div class="likeTest">
		<input type="text" id="replyno" placeholder="댓글 넘버">
		<button class="btn btn-primary btn-lg btn-block" id="likeReply">like Reply</button>
	</div>
	
	<hr/>
	
	<div class="replyinfo">
		<input type="text" id="rrpostno" placeholder="게시글 넘버">
		<input type="text" id="parentreplyno" placeholder="댓글 넘버">
		<input type="text" id="rreplyContent" placeholder="댓글 내용">
		<button id="insertreReply">대댓글만 달기</button>
		<button id="insertreReplyTag">대댓글 달고 공유하기</button>
	</div>
	
</body>

<script type="text/javascript">

$("document").ready(function(){
	var availableTags = [
        "ActionScript",
        "AppleScript",
        "Asp",
        "BASIC",
        "C",
        "C++",
        "Clojure",
        "COBOL",
        "ColdFusion",
        "Erlang",
        "Fortran",
        "Groovy",
        "Haskell",
        "Java",
        "JavaScript",
        "Lisp",
        "Perl",
        "PHP",
        "Python",
        "Ruby",
        "Scala",
        "Scheme"
      ];

	$("#searchbox").autocomplete({
		//autocomplete: 자동완성 기능 
		//source: 타이핑 시 보여질 내용 
		//select:fuction(event, ui){}: 아이템 선택 싯 실행, ui.item이 선택된 항목을 나타내는 객체
		//focus: function(event, ui){ return false;} : jQuery UI autocomplete를 한글과 사용할때 커서를 사용해서 아이템을 선택하면 나머지가 사라져 버리는 버그가 있다. 
		//												이 코드를 추가하면 해결된다. return false; 또는 event.preventDefault(); 를 사용해서 이벤트를 무시하게 하는 것이다.
		
				
		 source: availableTags,
	     select: function(event, ui) {
	         console.log(ui.item);
	     },
	     selectFirst: true,
	     minLength: 2,
	     open: function () {
             $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
         },
         close: function () {
             $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
         },   
         focus: function(event, ui) {
             return false;
         }
	})
});

$("document").ready(function(){
	$("#searchbox").autocomplete({
		//autocomplete: 자동완성 기능 
		//source: 타이핑 시 보여질 내용 
		//select:fuction(event, ui){}: 아이템 선택 싯 실행, ui.item이 선택된 항목을 나타내는 객체
		//focus: function(event, ui){ return false;} : jQuery UI autocomplete를 한글과 사용할때 커서를 사용해서 아이템을 선택하면 나머지가 사라져 버리는 버그가 있다. 
		//												이 코드를 추가하면 해결된다. return false; 또는 event.preventDefault(); 를 사용해서 이벤트를 무시하게 하는 것이다.
		
		source : function(request, response){
			var text = {value: request.term};
		 	var param = parsing(text);
			console.log("parameter: " + param); 
			if(param != null){
				$.ajax({
					url : "${pageContext.request.contextPath}/tag/searchFriends",
					type : "post",
					data : {value: param}, 
					//contentType : "application/json",
					//data : JSON.stringify(param),
					
					// 값을 돌려받을 때
					dataType: "json",
					success: function(data){
						response(
							$.map(data, function(item){
								return {
									label: item.name,
									value: item.name
								}
							})
						); 
					}
				});
			}else{
				console.log("no tag");
			}
		},
		minLength: 1,
		select: function(event,ui){},
		focus: function(event,ui){
			event.preventDefault();
		}
	});
});

function parsing(text){
	var content = text.value;
	var tag = "@"
	var result;
	if(content.indexOf(tag) != -1){
		result = content.substring(content.indexOf(tag)+1);
		return result;
	}
	else{ 
		return null;
	}
}

$("#sharePost").on("click",function(){
	
	var multiParam = Array();
	
	var tagList = [];
	tagList.push(3);
	tagList.push(4);
	
	var postvo = {
		postNo : $("#postno").val()
	};	
	
	multiParam.push(tagList);
	multiParam.push(postvo);
	console.log(multiParam)

	
	$.ajax({
			url : "${pageContext.request.contextPath}/tag/sharePost",
			type: "post",
			contentType : "application/json",
			data : JSON.stringify(multiParam),

			dataType : "json",
			success : function(result) {
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
});

$("#insertReply").on("click",function(){
	
	var multiParam = Array();
	
	var tagList = [];
	
	var replyvo = {
		postNo : $("#rpostno").val(),
		replyContent: $("#replyContent").val()
	};	
	
	multiParam.push(tagList);
	multiParam.push(replyvo);
	console.log(multiParam)

	
	$.ajax({
			url : "${pageContext.request.contextPath}/reply/writeReply",
			type: "post",
			contentType : "application/json",
			data : JSON.stringify(multiParam),

			dataType : "json",
			success : function(result) {
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
});

$("#insertReplyTag").on("click",function(){
	
	var multiParam = Array();
	
	var tagList = [];
	tagList.push(3);
	tagList.push(4);
	
	var replyvo = {
		postNo : $("#rpostno").val(),
		replyContent: $("#replyContent").val()
	};	
	
	multiParam.push(tagList);
	multiParam.push(replyvo);
	console.log(multiParam)

	
	$.ajax({
			url : "${pageContext.request.contextPath}/reply/writeReply",
			type: "post",
			contentType : "application/json",
			data : JSON.stringify(multiParam),

			dataType : "json",
			success : function(result) {
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
});

$("#likeReply").on("click",function(){
	replyvo = {
		replyNo: $("#replyno").val()
	}
	console.log(replyvo);
	
	$.ajax({
		url : "${pageContext.request.contextPath }/reply/likeReply",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(replyvo),

		dataType : "json",
		success : function(result) {
			if(result == 1)
				alert("success");
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
})

$("#insertreReply").on("click",function(){
	
	var multiParam = Array();
	
	var tagList = [];
	
	var replyvo = {
			replyNo : $("#parentreplyno").val(),
			postNo : $("#rrpostno").val(),
			replyContent: $("#rreplyContent").val()
	};	
	
	multiParam.push(tagList);
	multiParam.push(replyvo);
	console.log(multiParam)

	
	$.ajax({
			url : "${pageContext.request.contextPath}/reply/reReply",
			type: "post",
			contentType : "application/json",
			data : JSON.stringify(multiParam),

			dataType : "json",
			success : function(result) {
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
});

$("#insertreReplyTag").on("click",function(){
	
	var multiParam = Array();
	
	var tagList = [];
	tagList.push(3);
	tagList.push(4);
	
	var replyvo = {
		replyNo : $("#parentreplyno").val(),
		postNo : $("#rrpostno").val(),
		replyContent: $("#rreplyContent").val()
	};	
	
	multiParam.push(tagList);
	multiParam.push(replyvo);
	console.log(multiParam)

	
	$.ajax({
			url : "${pageContext.request.contextPath}/reply/reReply",
			type: "post",
			contentType : "application/json",
			data : JSON.stringify(multiParam),

			dataType : "json",
			success : function(result) {
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
});


</script>


</html>
