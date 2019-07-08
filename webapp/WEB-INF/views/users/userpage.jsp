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
	<button class="btn btn-primary btn-lg btn-block" id="follow" data-jeky="5">Follow jeky</button>
	<button class="btn btn-primary btn-lg btn-block" id="unfollow" data-jeky="5">UnFollow jeky</button>
</body>

<script type="text/javascript">
	$("#follow").on("click",function(){
		console.log("follow");
		
		var userrelationvo = {
			relationFrom: ${authUser.userNo},
			relationTo: $("#follow").data("jeky")
		}
		
		console.log(userrelationvo)
	})
	
	$("#unfollow").on("click",function(){
		console.log("unfollow");
		
		var userrelationvo = {
				relationFrom: ${authUser.userNo},
				relationTo: $("#follow").data("jeky")
		}
		
		console.log(userrelationvo);
	})
</script>
</html>