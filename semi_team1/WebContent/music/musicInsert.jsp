<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="/semi_team1/se2/js/HuskyEZCreator.js" charset="utf-8"></script>

</head>
<body>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h2>음악 등록하기</h2>
		<form action="/semi_team1/music/insert" method="post"
			class="navbar-form" name="f">
			<label>제목</label><br> <input type="text" class="form-control"
				name="title"><br> <label>가수</label><br> <input
				type="text" class="form-control" name="singer"> <br> <label>가사</label><br>
			<textarea name="lyrics" id="ir1" rows="10" cols="100"
				style="width: 766px; height: 412px; display: none;"></textarea>
			<br> <label>작곡가</label><br> <input type="text"
				class="form-control" name="songwriter"><br> <label>작사가</label><br>
			<input type="text" class="form-control" name="lyricist"><br>
			<label>이미지</label><br> <input type="text" class="form-control"
				name="music_img"> <br> <label>이미지등록</label><br> <input
				type="file" name="upFile" id="upFile"
				onchange="getCmaFileView(this,'name')"><br /> <input
				type="button" value="확장자 포함 파일명 보기"
				onclick="getCmaFileView('upFile','all');" /> <input
				class="btn btn-success" type="button" value="등록"
				onclick="submitContents(this);">
		</form>
	</div>
	<script type="text/javascript">
		var oEditors = [];

		// 추가 글꼴 목록
		//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "ir1",
			sSkinURI : "/semi_team1/se2/SmartEditor2Skin.html",
			htParams : {
				bUseToolbar : true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
				fOnBeforeUnload : function() {
					//alert("완료!");
				}
			}, //boolean
			fOnAppLoad : function() {
				//예제 코드
				//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
			},
			fCreator : "createSEditor2"
		});

		function pasteHTML() {
			var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
			oEditors.getById["ir1"].exec("PASTE_HTML", [ sHTML ]);
		}

		function showHTML() {
			var sHTML = oEditors.getById["ir1"].getIR();
			alert(sHTML);
		}

		function submitContents(elClickedObj) {
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.

			// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
			try {
				elClickedObj.form.submit();
			} catch (e) {
			}
		}

		function setDefaultFont() {
			var sDefaultFont = '궁서';
			var nFontSize = 24;
			oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
		}

		function getCmaFileInfo(obj, stype) {
			var fileObj, pathHeader, pathMiddle, pathEnd, allFilename, fileName, extName;
			if (obj == "[object HTMLInputElement]") {
				fileObj = obj.value
			} else {
				fileObj = document.getElementById(obj).value;
			}
			if (fileObj != "") {
				pathHeader = fileObj.lastIndexOf("\\");
				pathMiddle = fileObj.lastIndexOf(".");
				pathEnd = fileObj.length;
				fileName = fileObj.substring(pathHeader + 1, pathMiddle);
				extName = fileObj.substring(pathMiddle + 1, pathEnd);
				allFilename = fileName + "." + extName;

				if (stype == "all") {
					return allFilename; // 확장자 포함 파일명
				} else if (stype == "name") {
					return fileName; // 순수 파일명만(확장자 제외)
				} else if (stype == "ext") {
					return extName; // 확장자
				} else {
					return fileName; // 순수 파일명만(확장자 제외)
				}
			} else {
				alert("파일을 선택해주세요");
				return false;
			}
		}

		function getCmaFileView(obj, stype) {
			var s = getCmaFileInfo(obj, stype);
			var img = document.getElementsByName("music_img")[0];
			img.value = s;
		}
	</script>

</body>
</html>