<%@page import="com.team1.vo.BoardTastyVo"%>
<%@page import="com.sun.webkit.BackForwardList"%>
<%@page import="com.team1.dao.BoardTastyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="/semi_team1/se2/js/HuskyEZCreator.js" charset="utf-8">
	
</script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=841e8af7e9b2592bac8cd28d92447368"></script>
<script type="text/javascript">
	function checkid() {
		staticMapContainer = document.getElementById('staticMap');
		staticMapContainer.innerHTML = "";
		window.open("/semi_team1/tasty/map.html", "_blank",
				"width=800,height=500");
	}

	//지도API
	function getHTML() {
		var mapContainer = document.getElementById('staticMap');
		console.log(mapContainer.innerHTML);
	}

	function setMap(hb, ib) {
		// 이미지 지도에서 마커가 표시될 위치입니다 
		var markerPosition = new daum.maps.LatLng(ib, hb);

		// 이미지 지도에 표시할 마커입니다
		// 이미지 지도에 표시할 마커는 Object 형태입니다
		var marker = {
			position : markerPosition
		};

		staticMapContainer = document.getElementById('staticMap'), // 이미지 지도를 표시할 div  

		staticMapOption = {
			center : new daum.maps.LatLng(ib, hb), // 이미지 지도의 중심좌표
			level : 3, // 이미지 지도의 확대 레벨
			marker : marker
		// 이미지 지도에 표시할 마커 
		};

		// 이미지 지도를 생성합니다
		staticMap = new daum.maps.StaticMap(staticMapContainer, staticMapOption);
	}
</script>
</head>
<body>

	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<%
			request.setCharacterEncoding("utf-8");

			int num = Integer.parseInt(request.getParameter("num"));
			BoardTastyDao dao = new BoardTastyDao();
			BoardTastyVo vo = dao.select(num);

			//카테고리 번호
			String s_num = request.getParameter("s_num");
		%>
		<div style="margin: auto; width: 1000px">
			<form action="/semi_team1/tasty/update" method="post">
				<input type="hidden" value="<%=s_num%>" name="s_num"> <input
					type="hidden" name="addr" id="i_addr" value="<%=vo.getAddr()%>">
				<input type="hidden" name="map" id="i_map"> <input
					type="hidden" value="<%=num%>" name="num">
				<table class="table table-bordered">
					<tr>
						<td width="75">작성자</td>
						<td colspan="2"><input type="text" name="writer"
							value="${sessionScope.m_nick }" readonly="readonly"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td colspan="2"><input type="text" name="title_name"
							id="title_name" size="50" value="<%=vo.getTitle()%>"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2"><textarea name="content" id="ir1" rows="10"
								cols="100" style="width: 766px; height: 412px; display: none;"><%=vo.getContent()%></textarea>
							<!--textarea name="ir1" id="ir1" rows="10" cols="100" style="width :100%; height:412px; min-width:610px; display:none;"></textarea-->
					</tr>

					<tr>
						<td rowspan="2">지도</td>
						<td width="150" style="text-align: center"><input
							type="button" value="지도 첨부" onclick="checkid()"></td>
						<td id="addr"><%=vo.getAddr()%></td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="staticMap"
								style="width: 600px; height: 350px; margin: auto"><%=vo.getMap()%></div>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center"><input class="btn btn-success"
							type="button" value="수정" onclick="submitContents(this);">
							<input class="btn btn-success" type="button" value="목록"
							onclick="location.href = '/semi_team1/list';"></td>
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
			var content = document.getElementById("ir1").value;
			var title = document.getElementById("title_name").value;

			if (content == null || content == "<p>&nbsp;</p>") {
				alert("내용을 입력하세요!");
				return;
			} else if (title == null || title == "") {
				alert("제목을 입력하세요!");
				return;
			} else {
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