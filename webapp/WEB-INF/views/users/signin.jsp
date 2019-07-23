<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>signin page</title>

    <script type="text/javascript" src="${pageContext.request.contextPath }/assets/jquery/jquery-1.12.4.js"></script>  
      
</head>

<body>
    <div id="container" class="cls-container">
		<div id="bg-overlay"></div>
		<div class="cls-content">
		    <div class="cls-content-sm panel">
		        <div class="panel-body">
		            <div class="mar-ver pad-btm">
		                <h1 class="h3">SignUp Page</h1>
		                <p>Sign Up to your account</p>
		            </div>
		            <div class="form">
		            	 <div class="form-group">
		                    <input type="text" class="form-control" id="UserName" placeholder="UserName" autofocus>
		                </div>
		                <div class="form-group">
		                    <input type="text" class="form-control" id="ID" placeholder="ID" autofocus>
		                    <button id="duplicate">check ID</button>
		                </div>
		                <div id="informtext">
		                </div>
		                <div class="form-group">
		                    <input type="password" class="form-control" id="Password" placeholder="Password">
		                </div>
		                <button class="btn btn-primary btn-lg btn-block" id="signin-button">Sign In</button>
		            </div>
		        </div>
		    </div>
		</div>
    </div>
</body>

<script type="text/javascript">

	$("#signin-button").on("click",function(){
		
		uservo = {
			userName: $("#UserName").val(),
			userId: $("#ID").val(),
			userPwd: $("#Password").val(),
			userRepImg: ""
		};
		
		console.log(uservo);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/user",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(uservo),
			
			dataType : "json",
			success : function(result) {
				alert("you signed up successfully.");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});	
	
	$("#duplicate").on("click", function(){
		uservo={
				userId: $("#ID").val()
		}
		
		console.log(uservo);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/user/checkdupid",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(uservo),
			
			dataType : "json",
			success : function(result) {
				if(result == 1){
					$("#informtext").css("color", "green");
					$("#informtext").text("you can use this ID");
				}else{ 
					$("#informtext").css("color", "red");
					$("#informtext").text("this ID is already used");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
</script>

</html>
