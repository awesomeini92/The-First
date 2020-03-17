<%@page import="member.vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	MemberBean mb = (MemberBean)request.getAttribute("mb");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>
		<h1>회원정보 수정</h1>
		<form action="UpdatePro.me" method="post">
			<fieldset>
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="id" value=<%=mb.getId() %> readonly="readonly"></td>
					</tr>
					<tr>
						<td>닉네임</td>
						<td><input type="text" name="nickname" value=<%=mb.getNickname() %> required="required"></td>
					</tr>
					<tr>
						<td>패스워드</td>
						<td><input type="password" name="password" required="required"></td>
					</tr>
					<tr>
						<td>E-Mail</td>
						<td><input type="text" name="email" value=<%=mb.getEmail() %> required="required"></td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="submit" value="회원정보수정" />
							<input type="button" value="회원탈퇴" onClick="location.href='MemberDelete.me?id=<%=mb.getId()%>&password'">
							<input type="button" value="취소" onclick="history.back()" />
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</section>
</body>
</html>