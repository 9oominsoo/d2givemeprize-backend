<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Login page</title>

    <script type="text/javascript" src="${pageContext.request.contextPath }/assets/jquery/jquery-1.12.4.js"></script>  
      
</head>

<body>
    <div id="container" class="cls-container">
		<div id="bg-overlay"></div>
		<div class="cls-content">
		    <div class="cls-content-sm panel">
		        <div class="panel-body">
		            <div class="mar-ver pad-btm">
		                <h1 class="h3">Login Page</h1>
		                <p>Log In to your account</p>
		            </div>
		            <div class="form">
		                <div class="form-group">
		                    <input type="text" class="form-control" id="ID" placeholder="ID" autofocus>
		                </div>
		                <div class="form-group">
		                    <input type="password" class="form-control" id="Password" placeholder="Password">
		                </div>
		                <div class="checkbox pad-btm text-left">
		                    <input id="demo-form-checkbox" class="magic-checkbox" type="checkbox">
		                    <label for="demo-form-checkbox">Remember me</label>
		                </div>
		                <button class="btn btn-primary btn-lg btn-block" id="login-button">Log In</button>
		            </div>
		        </div>
		    </div>
		</div>
    </div>
</body>

<script type="text/javascript">

	$("#login-button").on("click",function(){
		uservo = {
			userId: $("#ID").val(),
			userPwd: $("#Password").val()
		}	
		
		console.log(uservo);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/user/login",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(uservo),
			
			dataType : "json",
			success : function(result) {
				console.log(result);
				$(location).attr("href", "${pageContext.request.contextPath}/user/loginsuccess")
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});	
	
</script>

</html>
