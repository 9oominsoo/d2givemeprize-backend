<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
		
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/jquery/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
	<title>Mysite</title>
</head>
<body>
	<div id="container">
		
		<div id="content">
			<div id="c_box">
				<div id="gallery">
					<h2>갤러리</h2>
					
					<input id="btnImgPop" class="btn btn-primary" type="button" value="이미지등록">
					
					<ul class="view">
						
					</ul>
				</div><!-- /gallery -->
			</div><!-- /c_box -->
		</div><!-- /content -->
			
		<!-- footer -->	
		
	</div><!-- /container -->
	
	
	
	
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="delPop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				<form id="ImgAdd" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="formgroup">
							<label>코멘트작성</label><br>
							<input type="hidden" name="userNo" id="userNo" value="${authUser.no }">
							<input type="text" name="comment" id="comment"><br>	
						</div>
						<div class="formgroup">
							<label>이미지선택</label><br>	
							<input type="file" name="file" value="" id="file"> <br>	
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary" id="btnImgAdd">등록</button>
					</div>
				</form>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
	
	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewPop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup">
						<img id="detailImg" src="" width="300px">
					</div>
					
					<div class="formgroup">
						<label>코멘트</label><br>
						<div id="detailComment"></div>
						
					</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-danger" id="btnDel" value="">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
</body>

<script type="text/javascript">

/* 화면에 업로드 된 파일(사진) 출력하기  */
$("document").ready(function(){
	$.ajax({
		url: "${pageContext.request.contextPath}/gallery/print",
		type: "post",
		dataType: "json",
		success : function(GalleryList) {
			/*성공시 처리해야될 코드 작성*/
			console.log(GalleryList);
			
			for(var i=0; i<GalleryList.length; i++){
				showGallery(GalleryList[i]);
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	})
});

/* 이미지등록 팝업(모달)창 띄우기*/ 
$("#btnImgPop").on("click", function() {
    $("#delPop").modal();
    
});

/* 이미지보기 팝업(모달)창 띄우기*/ 
$(".view").on("click", "#viewFile", function() {
	console.log("aaa");
	$("#viewPop").modal();
	console.log($(this).data("fileno"));
	var gallerydto = {
			no : $(this).data("fileno")
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/gallery/detailLook",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(gallerydto),
		
		dataType : "json",
		success : function(galleryDto) {
			$("#detailImg").attr("src", '${pageContext.request.contextPath }/upload/'+galleryDto.saveName);
			$("#detailComment").text(galleryDto.comments);
			$("#btnDel").val(galleryDto.no);
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	})
});

/* 이미지 업로드 */
$("#btnImgAdd").on("click", function(){
	console.log("이미지 등록 버튼 클릭");
	var comment = $("#comment").val();
	var userNo = $("#userNo").val();
	var file = $("#file")[0].files[0];
	// multipart는 formData 형식으로 넘겨주어야 한다.
	var formData = new FormData();
	formData.append("comment", comment);
	formData.append("userNo", userNo);
	formData.append("file", file);
	
	if($("#userNo").val() == ""){
		alert("로그인을 해주세요");
	}else{
		$.ajax({
			url : "${pageContext.request.contextPath }/gallery/upload",
			type: "post",
			data : formData,
			processData : false,
			contentType : false,
			
			dataType: "json",
			success : function(galleryDto) {
				/*성공시 처리해야될 코드 작성*/		
				$("#delPop").modal("hide");
				showGallery(galleryDto);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
	}
});

/* 이미지 삭제 */
$("#btnDel").on("click", function(){
	console.log("삭제 버튼을 클릭: " + $(this).val());
	
	var gallerydto={
			no : $(this).val(),
			userNo : $("#userNo").val()
	}
	
	if($("#userNo").val() == ""){
		alert("로그인을 해주세요");
	}else{
		$.ajax({
			url : "${pageContext.request.contextPath}/gallery/delete",
			type: "post",
			contentType : "application/json",
			data : JSON.stringify(gallerydto),

			dataType : "json",
			success : function(gallerydto) {
				$("[data-fileno ="+gallerydto.no+"]").remove();
				console.log(gallerydto.no)
			},
			error : function(XHR, status, error) {
				alert("삭제할 수 없습니다.")
				console.error(status + " : " + error);
			}
		})
	}
});

function showGallery(GalleryDto){
	var str="";
	str += "<li id='viewFile' data-fileno="+ GalleryDto.no + ">";
	str += "	<img src='${pageContext.request.contextPath}/upload/"+GalleryDto.saveName + "'>";
	str += "</li>"	
	
	$(".view").prepend(str);
}

</script>







</html>