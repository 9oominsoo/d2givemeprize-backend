<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Login page</title>

    <script type="text/javascript" src="${pageContext.request.contextPath }/assets/jquery/jquery-1.12.4.js"></script>  
    
    <!-- sockjs -->
	<script src="${pageContext.request.contextPath }/assets/sockjs/dist/sockjs.js"></script>
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
		            <div class="message">
		            	<div id="data">
		            	</div>
		            </div>
		        </div>
		    </div>
		</div>
    </div>
</body>

<script type="text/javascript">
	let webSock;
	
	$("document").ready(function(){
		// 웹소켓을 지정한 url로 연결한다.
		let sock = new SockJS("${pageContext.request.contextPath }/echo/");
		var authUserNo = '${authUser.userNo}';
		
		webSock = sock;

		webSock.onmessage = onMessage;
		webSock.onclose = onClose;
	});
	
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
				msg = {};
				msg.target = result.userName + "has log in...";
				webSock.send(JSON.stringify(msg));
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
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
	
</script>

</html>
