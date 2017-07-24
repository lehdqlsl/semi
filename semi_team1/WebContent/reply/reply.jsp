<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.comm{width:400px;height:100px;border:1px solid #aaa};
</style>
<script type="text/javascript">
var xhr=null;
function listAll(){
	// 댓글전체 목록을 ajax로 받아와서 commList에 출력하기
	xhr=new XMLHttpRequest();
	xhr.onreadystatechange=getList;
	xhr.open("get","reply.list",true);
	xhr.send();
}
function getList(){
	if(xhr.readyState==4 && xhr.status==200){
		delComm(); // 기존의 전체댓글목록 지우기
		var data=xhr.responseText;
		var json=JSON.parse(data);
		var list=document.getElementById("commlist");
		for(var i=0;i<list.length;i++){
			var comm=json[i];
			var num=comm.num;
			var id=comm.id;
			var comments=comm.comments;
			var div=document.createElement("div");
			console.log(num + id + comments);
			var html="아이디:" +id +"<br>" + "댓글:" +comments + "<br>" + 
						  "<a href='javascript:remove(" + num + ")'>삭제</a>";
		
			div.innerHTML=html;
			div.className="comm";
			list.appendChild(div);
			
		}
	}
}
</script>
</head>
<body onload="listAll()">
<div id="reply">
	댓글 | 총 [ ]개 <!-- 총 댓글수 dao에 cntTot생성 -->
	1 2 3 4 5<!--  가운데 페이징 목록 -->
	<input type="button" value="댓글쓰기">
</div>

<div>
	<!--  댓글목록이 보여질 div -->
	<div id="commlist">
		<table border="1" width="500">
	<tr>
		<th>답글번호</th><th>작성자</th><th>내용</th><th>추천수</th><th>등록일</th><th>게시글번호</th><th>신고유무</th><th>답글</th>
	</tr>
	<c:forEach var="vo" items="${requestScope.list }"> <!--  items에는 배열이나 collection 객체를 담는다 -->
		<tr>
			<td>${vo.r_num }</td>
			<td>${vo.nick }</td>
			<td>${vo.content}</td>
			<td>${vo.up }</td>
			<td>${vo.reg_date }</td>
			<td>${vo.num }</td>
			<td>${vo.report }</td>
			
			<!--  삭제성공이면 목록이 보이도록, 실패하면 result.jsp에서 결과 출력 -->
			<td><a href="jsp12_myusers/myusers.do?cmd=insert&id=${vo.nick }">답글</a></td>
		</tr>
	</c:forEach>
	</div>
</div>

</body>
</html>