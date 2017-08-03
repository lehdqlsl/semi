<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function getCmaFileInfo(obj,stype) {
    var fileObj, pathHeader , pathMiddle, pathEnd, allFilename, fileName, extName;
    if(obj == "[object HTMLInputElement]") {
        fileObj = obj.value
    } else {
        fileObj = document.getElementById(obj).value;
    }
    if (fileObj != "") {
            pathHeader = fileObj.lastIndexOf("\\");
            pathMiddle = fileObj.lastIndexOf(".");
            pathEnd = fileObj.length;
            fileName = fileObj.substring(pathHeader+1, pathMiddle);
            extName = fileObj.substring(pathMiddle+1, pathEnd);
            allFilename = fileName+"."+extName;
 
            if(stype == "all") {
                    return allFilename; // 확장자 포함 파일명
            } else if(stype == "name") {
                    return fileName; // 순수 파일명만(확장자 제외)
            } else if(stype == "ext") {
                    return extName; // 확장자
            } else {
                    return fileName; // 순수 파일명만(확장자 제외)
            }
    } else {
            alert("파일을 선택해주세요");
            return false;
    }
    // getCmaFileView(this,'name');
    // getCmaFileView('upFile','all');
 }
 
function getCmaFileView(obj,stype) {
    var s = getCmaFileInfo(obj,stype);
    var img=document.getElementsByName("g_img")[0];
    img.value=s;
}
</script>
</head>
<body>
<div class="container">
		<div style="width: 500px; height: 500px; margin: auto;">
			<h1>게임 정보 등록</h1>
			<form action="/semi_team1/game/insert" method="post"
				class="navbar-form" name="f">
				<label>게임이름</label><br> 
				<input type="text"  class="form-control" name="g_name"><br>
				<label>장르</label><br> 
				<input type="text"  class="form-control" name="g_jenre"> <br>
				<label>기종</label><br> 
				<input type="text"class="form-control" name="flatform"><br>
				<label>회사</label><br> 
				<input type="text" class="form-control" name="company"><br> 
				<label>출시일</label><br>
				<input type="text" class="form-control" name="l_date"> <br>
				<label>이미지</label><br>
				<input type="text" class="form-control" name="g_img"> <br>
				<label>이미지등록</label><br> 
					 <input type="file" name="upFile" id="upFile" onchange="getCmaFileView(this,'name')"><br /><br />
 					<input type="button" value="확장자 포함 파일명 보기" onclick="getCmaFileView('upFile','all');" />
					<input type="submit" value="등록" >
			</form>
		</div>
	</div>
</body>
</html>