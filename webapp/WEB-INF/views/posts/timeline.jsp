<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>TIMELINE page</title>

    <script type="text/javascript" src="${pageContext.request.contextPath }/assets/jquery/jquery-1.12.4.js"></script>  
     <!-- sockjs -->
	<script src="${pageContext.request.contextPath }/assets/sockjs/dist/sockjs.js"></script>
</head>
<body>
	<div class="form">
		<div class="form-group">
			<input type="text" class="form-control" id="PostTitle" placeholder="PostTitle" autofocus>
		</div>
		<div class="form-group">
			<input type="text" class="form-control" id="PostContent" placeholder="PostContent" autofocus>
		</div>
		<div class="form-group">
			<input type="password" class="form-control" id="PostImg" placeholder="PostImg">
		</div>
		<button class="btn btn-primary btn-lg btn-block" id="writePheed-button">write pheed</button>
	</div>
	
	<div class="likeTest">
		<button class="btn btn-primary btn-lg btn-block" id="likePost" data-postno="7">like Post</button>
	</div>
	
	<div class="hitTest">
		<button class="btn btn-primary btn-lg btn-block" id="detailPost" data-postno="7">Detail</button>
	</div>
	
	<div id="data"></div>
</body>

<script type="text/javascript">
/*
	$("document").ready(function() {
		console.log("load all pheed...");

		$.ajax({
			url : "${pageContext.request.contextPath }/post",
			contentType : "application/json",
			type: "get",

			dataType : "json",
			success : function(result) {
				console.log(result);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	*/
	
	$("document").ready(function(){
		// 웹소켓을 지정한 url로 연결한다.
		let sock = new SockJS("${pageContext.request.contextPath }/echo/");
		
		webSock = sock;

		webSock.onmessage = onMessage;
		webSock.onclose = onClose;
	});
	
	// 메시지 전송
    function sendMessage() {
           sock.send("하이");
    }

    // 서버로부터 메시지를 받았을 때
    function onMessage(msg) {
           var data = msg.data;
           $("#data").append(data + "<br/>");
    }

    // 서버와 연결을 끊었을 때
    function onClose(evt) {
           $("#data").append("연결 끊김");
    }
	

	
	$("#writePheed-button").on("click",function(){
		
		var multiParam = Array();
		
		var imgList = [];

		var tagList = [];
		
		tagList.push(9);
		tagList.push(16);
		
		postvo = {
			postContent: $("#PostContent").val(),
		}
		
		multiParam.push(imgList);
		multiParam.push(tagList);
		multiParam.push(postvo);
		
		console.log(multiParam)
		
		console.log(postvo);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/post",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(multiParam),

			dataType : "json",
			success : function(result) {
				if(result != ""){
					console.log(result)
					msg = {};
					msg.msg = result.userName + result.PheedMessage;
					webSock.send(JSON.stringify(msg));					
					alert("success to write pheed");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	$("#likePost").on("click",function(){
		postvo = {
			postNo: $("#likePost").data("postno")
		}
		console.log(postvo);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/post/likePheed",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(postvo),

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
	
	$("#detailPost").on("click",function(){
		postvo = {
			postNo: $("#detailPost").data("postno")
		}
		console.log(postvo);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/post/detailPheed",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(postvo),

			dataType : "json",
			success : function(postVo) {
					console.log(postVo)
					alert("success");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	})

</script>

</html>