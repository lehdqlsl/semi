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
		<%
			request.setCharacterEncoding("utf-8");
			String scnt = request.getParameter("cnt");
			String writer = request.getParameter("writer");
			String title_name = request.getParameter("title_name");
			String content = request.getParameter("content");

			//카테고리 번호
			String s_num = request.getParameter("s_num");
			int cnt = 0;

			if (writer == null) {
				writer = "";
				title_name = "";
				content = "";
			}
		%>
		<div style="margin: auto; width: 1000px">
			<form action="/semi_team1/insert" method="post">
				<input type="hidden" value="<%=s_num%>" name="s_num">
				<table class="table table-bordered">
					<tr>
						<td>작성자</td>
						<td><input type="text" name="writer"
							value="${sessionScope.m_nick }" readonly="readonly"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="title_name" id="title_name" size="100"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea name="content" id="ir1" rows="10" cols="100"
								style="width: 766px; height: 412px; display: none;"></textarea>
							<!--textarea name="ir1" id="ir1" rows="10" cols="100" style="width:100%; height:412px; min-width:610px; display:none;"></textarea-->
					</tr>
					<tr>
						<td colspan="2" align="center"><input class="btn btn-success"
							type="button" value="확인" onclick="submitContents(this);">
							<input class="btn btn-success" type="button" value="이전"
							onclick="javascript:history.back()"></td>
					</tr>
				</table>
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
			var content=document.getElementById("ir1").value;
			var title=document.getElementById("title_name").value;
			console.log("내용 : "+content);
			if(content==null || content=="<p>&nbsp;</p>"){
				alert("내용을 입력하세요!");
				return;
			}else if(title==null || title==""){
				alert("제목을 입력하세요!");
				return;
			}else{
				try {
					elClickedObj.form.submit();
				} catch (e) {
				}
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