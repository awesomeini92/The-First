<%@page import="java.io.PrintWriter"%>
<%@page import="member.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String nickname = null;
	
	if (session.getAttribute("nickname") != null) {
		nickname = (String)session.getAttribute("nickname");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	header {
	text-align: right;
	}
</style>
</head>
<body>
<header>
<%if(nickname == null) {  %>
	<a href="LoginForm.me">로그인</a> | <a href="JoinForm.me">회원가입</a>
<%} else { %>
	${nickname }님  | <a href="Mypage.me">마이페이지</a> | <a href="Logout.me">로그아웃</a>
<%} %>
</header>
<h1>http://localhost:8080/The_First/</h1>
<h1><a href=" JobList.job">커뮤니티-취업</a></h1>
<h1><a href=" AcademyList.ac">커뮤니티-학원</a></h1>
<h1><a href=" CommunityList.any">자유게시판</a></h1>
<h1><a href=" ShopList.shop">CP구매</h1>
<h1><a href=" CodingList.code">CodingQ&A</h1>
</body>
</html>