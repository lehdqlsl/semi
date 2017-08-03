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

	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div
			style="margin: auto; width: 800px; word-break: break-all; word-wrap: break-word;">
			<h1>영화 정보 등록</h1>
			<form method="post" action="/semi_team1/movieinsert"
				enctype="multipart/form-data" class="navbar-form">
				<label>영화제목</label><br> <input type="text" name="m_name"
					class="form-control"><br> <label>장르</label><br> <input
					type="text" name="m_genre" class="form-control"><br> <label>제작국가</label><br>
				<input type="text" name="m_country" class="form-control"><br>
				<label>상영시간</label><br> <input type="text" name="m_rt"
					class="form-control"><br> <label>개봉일자</label><br>
				<input type="text" name="m_release" class="form-control"><br>
				<label>상영등급</label><br> <input type="text" name="m_rate"
					class="form-control"><br> <label>감독</label><br> <input
					type="text" name="m_director" class="form-control"><br>
				<label>출연</label><br> <input type="text" name="m_actor"
					class="form-control"><br> <label>동영상링크</label><br>
				<input type="text" name="link" class="form-control"><br>
				<label>줄거리</label><br>
				<textarea name="story" id="ir1" rows="10" cols="100"
					style="width: 766px; height: 412px; display: none;"></textarea>
				${vo.story } <br> <label>첨부파일</label><br> <input
					type="file" name="file1"><br> <input class="btn btn-default"
							type="button" value="등록" onclick="submitContents(this);"> <input type="reset"
					class="btn btn-default" value="리셋">

			</form>
		</div>
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
	</script>
</body>
</html>