<%@page import="any_community.vo.PageInfo"%>
<%@page import="any_community.vo.CommunityBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<CommunityBean> articleList = (ArrayList<CommunityBean>)request.getAttribute("articleList");
	PageInfo pageInfo2 = (PageInfo)request.getAttribute("pageInfo");
	
	int listCount = pageInfo2.getListCount();
	int nowPage = pageInfo2.getPage(); 
	int startPage = pageInfo2.getStartPage();
	int endPage = pageInfo2.getEndPage();
	int maxPage = pageInfo2.getMaxPage();
	
	//
	System.out.println("nowPage = " + nowPage);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<%if(articleList != null & listCount > 0) {%>
			<%for(int i = 0; i < articleList.size(); i++) {%>
				<tr>
					<td align="center"><%=articleList.get(i).getNum() %></td>
					<td>
					<!--  게시물 제목 클릭시 BoardDetail.bo 요청 (게시물번호, 페이지번호 파라미터로 전달) -->
						<a href="CommunityDetail.any?num=<%=articleList.get(i).getNum()%>&page=${nowPage}">
							<%=articleList.get(i).getSubject()%>
						</a>
					</td>
					<td align="center"><%=articleList.get(i).getNickname() %></td>
					<td align="center"><%=articleList.get(i).getDate() %></td>
					<td align="center"><%=articleList.get(i).getReadcount() %></td>
				</tr>
			<%} %>
		</table>		
	</section>
	
	<section id="pageList">
	<%if(nowPage <= 1) { %>
			[이전]&nbsp;
	<%} else {%>
			<a href="CommunityList.any?page=<%=nowPage - 1%>">[이전]</a>&nbsp;
	<%} %>
	
	<%for(int i = startPage; i <= endPage; i++) {
		    if(i == nowPage) {%>
				[<%=i %>]
		<%} else {%>
				<a href="CommunityList.any?page=<%=i %>">[<%=i %>]</a>&nbsp;
		<%} %>
	<%} %>
	
	<%if(nowPage >= maxPage) {%>
			&nbsp;[다음]
	<%} else { %>
			<a href="CommunityList.any?page=<%=nowPage + 1%>">&nbsp;[다음]</a>
	<%} %>
	</section>
<%} else {%>
	<section id="emptyArea">등록된 글이 없습니다.</section>
<%} %>

	<section id="writeButton">
		<a href="CommunityWriteForm.any"><input type="button" value="글쓰기"></a>
	</section>
	
</body>
</html>
