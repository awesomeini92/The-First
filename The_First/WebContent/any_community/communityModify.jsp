<%@page import="any_community.vo.CommunityBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	CommunityBean article = (CommunityBean)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
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
		width: 450px;
		border: 1px solid darkgray;
	}
	
	.td_left {
		width: 150px;
		background: orange;
	}
	
	.td_right {
		width: 300px;
		background: skyblue;
	}
	
	#commandCell {
		text-align: center;
	}
</style>
</head>
<body>
	<section id="writeForm">
		<h2>게시판 글 수정</h2>
		<form action="CommunityModifyPro.any" method="post" name="boardForm">
			<input type="hidden" name="num" value="<%=article.getNum() %>" />
			<input type="hidden" name="page" value="<%=nowPage%>" />
			<table>
				<tr>
					<td class="td_left"><label for="board_name">글쓴이</label></td>
					<td class="td_right">
						<input type="text" name="name" id="board_name" value="<%=article.getNickname() %>" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_subject">제목</label></td>
					<td class="td_right">
						<input type="text" name="subject" id="board_subject" value="<%=article.getSubject() %>" required="required" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_content">내용</label></td>
					<td class="td_right"><textarea name="content" id="board_content" cols="40" rows="15" required="required" ><%=article.getContent() %></textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록" />&nbsp;&nbsp;
				<input type="button" value="뒤로" onclick="history.back()" />
			</section>
		</form>	
	</section>
</body>
</html>