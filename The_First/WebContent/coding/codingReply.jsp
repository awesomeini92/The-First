<%@page import="coding.vo.CodingBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sId = null;
/*
	// 로그인이 되지 않은 상태일 경우 로그인 페이지로 강제 이동 처리
	if(session.getAttribute("sId") == null) {
		out.println("<script>");
	    out.println("alert('로그인이 필요한 서비스입니다!')");
	    out.println("location.href='LoginForm.me'");
	    out.println("</script>");
	} else { // 로그인 된 상태일 경우 세션 ID 가져오기
		sId = (String)session.getAttribute("sId");
	}
*/
	CodingBean article = (CodingBean)request.getAttribute("article");
	CodingBean article_ref = (CodingBean)request.getAttribute("article_ref");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">
	#replyForm {
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
	
	textarea {
		resize: none;
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
	header {
		text-align: right;
	}
</style>
</head>
<body>
	<header>
		<!-- 세션ID(sId) 가 없을 경우 로그인(LoginForm.me), 회원가입(JoinForm.me) 링크 표시 -->
		<!-- 세션ID(sId) 가 있을 경우 회원ID, 로그아웃(Logout.me)링크 표시 -->
		<%if(sId == null) {%>
			<a href="LoginForm.me">로그인</a> | <a href="JoinForm.me">회원가입</a>
		<%} else { %>
			<%=sId %>님 | <a href="Logout.me">로그아웃</a>
		<%} %>
	</header>
	
	<!-- 기존 글 조회 -->
	<section id="articleForm">
		<h2>글 내용 상세보기</h2>
		<section id="basicInfoArea">
			제목 : <%=article.getSubject() %><br>
			첨부파일 : 
			<%if(article.getFile() != null) { %>
				<!-- 파일이름 클릭 시 새창에서 다운로드 작업 수행 -->	
				<a href="CodingFileDown.code?file_name=<%=article.getFile()%>" target="blank"><%=article.getFile() %></a>
			<%}%>
		</section>
		<section id="articleContentArea">
			<%=article.getContent() %> <br>
			<img src="/codingUpload/<%=article.getFile()%>" width=200px >	
		</section>
	</section>
	
	<!-- 게시판 글 답변 -->
	<section id="writeForm">
		<h2>게시판 답변글 등록</h2>
		<form action="CodingReplyPro.code" method="post" name="replyForm" enctype="multipart/form-data">
			<input type="hidden" name="post_num" value="<%=article.getNum() %>">
			<table>
				<tr>
					<td class="td_left"><label for="nickname">글쓴이</label></td>
					<td class="td_right"><input type="text" name="nickname"  id="nickname" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="subject">제목</label></td>
					<td class="td_right"><input type="text" name="subject" id="subject" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td class="td_right">
						<textarea name="content" id="content" cols="40" rows="15" required="required" ></textarea>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="ref_file">파일 첨부</label></td>
					<td class="td_right"><input name="ref_file"  type="file"  id="ref_file" required="required" /></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="답변글등록" />&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" />
			</section>
		</form>	
	</section>
</body>
</html>