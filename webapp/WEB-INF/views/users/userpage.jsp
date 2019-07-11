<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>follow/unfollow</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/jquery/jquery-1.12.4.js"></script>  
</head>
<body>
	<input type="hidden" id="selected-user" data-userno="${selectedUser.userNo }" data-userName="${selectedUser.userName }"></input>
	<div id="button"></div>
</body>

<script type="text/javascript">
	$("document").ready(function(){
		var userNo = $("#selected-user").data("userno");
		console.log(userNo +" page!")
		
		$.ajax({
			url : "${pageContext.request.contextPath }/user/"+userNo+"/checkuserrelation",
			type : "post",
			
			dataType : "json",
			success : function(userrelationvo) {
				if(userrelationvo == "" || userrelationvo=="undefined"||userrelationvo==null){
					$("#button").append('<button class="btn btn-primary btn-lg btn-block" id="follow" >Follow ${selectedUser.userName }</button>');
				}else{
					$("#button").append('<button class="btn btn-primary btn-lg btn-block" id="unfollow" >UnFollow ${selectedUser.userName }</button>')
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	$("#follow").on("click",function(){
		console.log("follow");
		
	});
	
	$("#unfollow").on("click",function(){
		console.log("unfollow");
		
	}); 
</script>
</html>