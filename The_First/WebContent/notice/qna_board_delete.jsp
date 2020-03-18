<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sId = null;
	// 로그인이 되지 않은 상태일 경우 로그인 페이지로 강제 이동 처리
	if(session.getAttribute("sId") == null) {
		out.println("<script>");
	    out.println("alert('로그인이 필요한 서비스입니다!')");
	    out.println("location.href='LoginForm.me'");
	    out.println("</script>");
	} else { // 로그인 된 상태일 경우 세션 ID 가져오기
		sId = (String)session.getAttribute("sId");
	}

	// 전달받은 request 객체로부터 게시물 번호(board_num)와 페이지번호(page) 가져오기
    int num = Integer.parseInt(request.getParameter("num"));
    String nowPage = request.getParameter("page");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style type="text/css">
	#passForm {
		width: 300px;
		margin: auto;
		border: 1px solid orange;
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
	
	<section id="passForm">
		<form name="deleteForm" action="NoticeDeletePro.no?num=<%=num %>&page=<%=nowPage %>" method="post">
<%-- 			<input type="hidden" name="num" value="<%=num %>" /> --%>
<%-- 			<input type="hidden" name="page" value="<%=nowPage %>" /> --%>
			
			<table>
				<tr>
					<td>
						<label>글 비밀번호</label><br>
						<input type="password" name="board_pass" required="required" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="삭제" />
						&nbsp;&nbsp;<input type="reset" value="돌아가기" onclick="history.back()"/>
					</td>
				</tr>
			</table>
		</form>
	</section>
	
</body>
</html>