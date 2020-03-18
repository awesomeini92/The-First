<%@page import="vo.NoticeBean"%>
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
	
	// 전달받은 request 객체에서 데이터 가져오기
	NoticeBean article = (NoticeBean)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style type="text/css">
	#articleForm {
		width: 500px;
		height: 500px;
		border: 1px solid red;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	#basicInfoArea {
		height: 40px;
		text-align: center;
	}
	
	#articleContentArea {
		background: orange;
		margin-top: 20px;
		height: 350px;
		text-align: center;
		overflow: auto; /* 지정 영역 크기 이상일 경우 자동으로 스크롤바 생성*/
	}
		
	#commandList {
		matgin: auto;
		width: 500;
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
	
	<!-- 게시판 글 조회 -->
	<section id="articleForm">
		<h2>글 내용 상세보기</h2>
		<section id="basicInfoArea">
			제목 : <%=article.getSubject() %><br>
			첨부파일 : 
			<%if(article.getFile() != null) { %>
				<!-- 파일이름 클릭 시 새창에서 다운로드 작업 수행 -->	
				<a href="NoticeFileDown.no?file_name=<%=article.getFile()%>" target="blank"><%=article.getFile() %></a>
			<%}%>
		</section>
		<section id="articleContentArea">
			<%=article.getContent() %>	
		</section>
	</section>
	<section id="commandList">
		<a href="NoticeModifyForm.no?num=<%=article.getNum()%>&page=<%=nowPage %>"><input type="button" value="수정" ></a>
		<a href="NoticeDeleteForm.no?num=<%=article.getNum()%>&page=<%=nowPage %>"><input type="button" value="삭제" ></a>
		<a href="NoticeList.no?page=<%=nowPage %>"><input type="button" value="목록" ></a>
	</section>
</body>
</html>


















