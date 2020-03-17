<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String nickname = (String)session.getAttribute("nickname");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글 쓰기</h1>
	<section>
		<h2>게시판글등록</h2>
		<form action="CommunityWritePro.any" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>닉네임</td>
					<td><input type="text" name="any_nickname" value="<%=nickname%>" readonly="readonly"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td class="td_right"><input type="text" name="any_subject" required="required"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea id="BOARD_CONTENT" name="any_content" cols="40" rows="15" required="required"></textarea></td>
				</tr>
				<tr>
					<td>파일 첨부</td>
					<td><input type="file" name="any_file" required="required"></td>
				</tr>
			</table>
			<section>
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">
			</section>
		</form>
	</section>
</body>
</html>