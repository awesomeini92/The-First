<%@page import="coding.vo.PageInfo"%>
<%@page import="coding.vo.CodingBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
	// Action 클래스에서 request 객체의 setAttibute() 메서드로 저장되어 전달된 객체 가져오기(Object 타입이므로 형변환 필요)
	ArrayList<CodingBean> articleList = (ArrayList<CodingBean>)request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	
	// PageInfo 객체로부터 페이징 정보 가져오기
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage(); // page 디렉티브의 이름과 중복되므로 nowPage 라는 변수명으로 사용
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int maxPage = pageInfo.getMaxPage();
	
%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
 <style type="text/css">
	#registForm {
		width: 500px;
		height: 610px;
		border: 1px solid red;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 800px;
		border: 1px solid darkgray;
	}
	
	a {
		text-decoration: none;
	}

	#tr_top {
		background: orange;
		width: 800px; 
		text-align: center;
	}
	
	#writeButton {
		margin: auto;
		width: 800px;
		text-align: right;
	}
	
	#pageList {
		margin: auto;
		width: 800px;
		text-align: center;
	}
	
	#emptyArea {
		margin: auto;
		width: 800px;
		text-align: center;
	}
</style>
</head>
<body>
<div id="wrap">
<%
String id = (String)session.getAttribute("id");
String nickname = (String)session.getAttribute("nickname");
%>
<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->


<!-- 게시판 -->


<!-- 게시판 리스트 -->
	<section id="listForm">
		<h2>글 목록</h2>
		<table>
			<tr id="tr_top">
				<td width="100">번호</td>
				<td width="400">제목</td>
				<td width="150">작성자</td>
				<td width="150">날짜</td>
				<td width="100">조회수</td>
			</tr>
		<%if(articleList != null) { //& listCount > 0) %> 
			<!-- 게시물 목록이 하나라도 존재할 때만 목록을 표시 -->
			<%for(int i = 0; i < articleList.size(); i++) {%>
				<tr>
					<td align="center"><%=articleList.get(i).getNum() %></td>
					<td>
					
						<!-- 게시물 제목 클릭 시 BoardDetail.bo 요청(게시물번호, 페이지번호 파라미터로 전달) -->
						<a href="CodingDetail.code?num=<%=articleList.get(i).getNum()%>&page=<%=nowPage%>">
							<%=articleList.get(i).getSubject() %>
						</a>
					</td>
					<td align="center"><%=articleList.get(i).getNickname() %></td>
					<td align="center"><%=articleList.get(i).getDate() %></td>
					<td align="center"><%=articleList.get(i).getReadcount() %></td>
				</tr>
			<%} %>
		</table>		
	</section>
	
	<section id="writeButton">
		<a href="CodingWriteForm.code"><input type="button" value="글쓰기"></a>
	</section>
	<!-- 페이지 목록 버튼 표시 -->
	<!-- 이전 페이지 또는 다음 페이지가 존재할 경우에만 해당 하이퍼링크 활성화 -->
	<section id="pageList">
	<%if(nowPage <= 1) { %>
			[이전]&nbsp;
	<%} else {%>
			<a href="CodingList.code?page=<%=nowPage - 1%>">[이전]</a>&nbsp;
	<%} %>
	
	<!-- 존재하는 페이지 번호만 표시(현재 페이지는 번호만 표시하고, 나머지 페이지는 하이퍼링크 활성화) -->
	<%for(int i = startPage; i <= endPage; i++) {
		    if(i == nowPage) {%>
				[<%=i %>]
		<%} else {%>
				<a href="CodingList.code?page=<%=i %>">[<%=i %>]</a>&nbsp;
		<%} %>
	<%} %>
	
	<%if(nowPage >= maxPage) {%>
			&nbsp;[다음]
	<%} else { %>
			<a href="CodingList.code?page=<%=nowPage + 1%>">&nbsp;[다음]</a>
	<%} %>
	</section>
<%} else {%>
	<section id="emptyArea">등록된 글이 없습니다.</section>
<%} %>


<div id="table_search">
<form action="noticeSearch.jsp">
<input type="text" name="search" class="input_box">
<input type="submit" value="search" class="btn" >
</form>
</div>


<div id="table_search">
<input type="button" value="글쓰기" class="btn" onclick="location.href='CodingWriteForm.code'">
<input type="button" value="글채택하기" class="btn" onclick="location.href='CodingUpdateForm.code'">
</div>


</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<%-- <jsp:include page="../inc/bottom.jsp"/> --%>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>