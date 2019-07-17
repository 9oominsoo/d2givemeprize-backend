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
</body>

<script type="text/javascript">
	$("document").ready(function() {
		console.log("load all pheed...");

		$.ajax({
			url : "${pageContext.request.contextPath }/post/loadMyPheed",
			contentType : "application/json",

			dataType : "json",
			success : function(result) {
				console.log(result);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	$("#writePheed-button").on("click",function(){
		postvo = {
			postTitle: $("#PostTitle").val(),
			postContent: $("#PostContent").val(),
			postImg: $("#PostImg").val()
		}
		console.log(postvo);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/post/writePheed",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(postvo),

			dataType : "json",
			success : function(result) {
				if(result == 1)
					alert("success to write pheed");
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