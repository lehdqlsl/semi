
<%@page import="org.jsoup.Jsoup"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@page import="org.jsoup.select.Elements"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Document doc1 = Jsoup.connect("http://score.sports.media.daum.net/record/soccer/epl/trnk.daum").get();
		Elements test1 = doc1.select("tbody");
		
		String str[] = test1.text().split(" ");

		ArrayList<String> list[] = new ArrayList[20];

		int z = 0;
		for (int i = 0; i < 20; i++) {
			list[i] = new ArrayList<>();
			for (int j = 0; j < 11; j++) {
				if (str[z].contains("W") || str[z].contains("C")) {
					z++;
				}
				list[i].add(str[z++]);
			}
		}

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 11; j++) {
				String str1 = list[i].get(1);
				if (str1.contains("W")) {
					list[i].set(1, "웨스트브로미치");
				} else if (str1.contains("C")) {
					list[i].set(1, "크리스탈팰리스");
				}
			}
		}
	%>
	<!-- 사이드바 -->
	<jsp:include page="sideBar.jsp"></jsp:include>

	<!-- 메인페이지 -->
	<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		<div
			style="margin: auto; width: 1000px; word-break: break-all; word-wrap: break-word; height: 35px; background-color: #428bca; margin-bottom: 0px; padding: 1px;">
			<h5 style="margin-left: 10px; font-weight: bold; color: white;">기록 순위</h5>
		</div>
		
		<div style="width: 1000px; word-break: break-all; word-wrap: break-word; margin: auto;">
			<table class="table table-striped">
				<tr align="center">
					<th>순위</th>
					<th>팀</th>
					<th>경기</th>
					<th>승</th>
					<th>무</th>
					<th>패</th>
					<th>득점</th>
					<th>실점</th>
					<th>득실차</th>
					<th>승점</th>
					<th>최근 5경기</th>
				</tr>

				<%
					for (int i = 0; i < 20; i++) {
				%>
				<tr>
					<%
						for (String s : list[i]) {
					%>
					<td><%=s%></td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>

			</table>
		</div>
	</div>
</body>
</html>