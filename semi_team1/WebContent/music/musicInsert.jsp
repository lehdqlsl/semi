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
			<h1>음악 정보 등록</h1>
			<form action="/semi_team1/music/insert" method="post"
				class="navbar-form" name="f">
				<label>제목</label><br> 
				<input type="text"  class="form-control" name="title"><br>
				<label>가수</label><br> 
				<input type="text"  class="form-control" name="singer"> <br>
				<label>가사</label><br> 
				<input type="text"class="form-control" name="lyrics"><br>
				<label>작곡가</label><br>
				<input type="text" class="form-control" name="songwriter"><br> 
				<label>작사가</label><br>
				<input type="text" class="form-control" name="lyricist"><br> 
				<label>이미지등록</label><br>
				<input type="file" name="upFile" id="upFile" onchange="getCmaFileView(this,'name')"><br/>
 				<input type="button" value="확장자 포함 파일명 보기" onclick="getCmaFileView('upFile','all');" />
				<input type="submit" value="등록" >
			</form>
		</div>
	</div>
</body>
</html>