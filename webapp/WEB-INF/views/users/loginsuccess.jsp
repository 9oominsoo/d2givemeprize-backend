<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script type="text/javascript" src="${pageContext.request.contextPath }/assets/jquery/jquery-1.12.4.js"></script>  
</head>
<body>
	<div>
		${authUser.userName }님 로그인 하셨습니다.
	</div>
	<button class="btn btn-primary btn-lg btn-block" id="logout-button">Log out</button>
</body>

<script type="text/javascript">
	$("#logout-button").on("click", function(){
		console.log("logout");
		
		$.ajax({
			url : "${pageContext.request.contextPath }/user/logout",
			type : "post",
			
			dataType : "json",
			success : function(result) {
				$(location).attr("href", "${pageContext.request.contextPath}/loginform")
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
</script>

</html>