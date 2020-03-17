<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section id="loginFormArea">
		<h1>로그인</h1>
		<form action="LoginPro.me" method="post" name="loginForm">
			<fieldset>
				<table>
					<tr>
						<td>
							<label for="id">아이디 : </label>
						</td>
						<td>
							<input type="text" name="id">
						</td>
					</tr>
					<tr>
						<td>
							<label for="id">패스워드 : </label>
						</td>
						<td>
							<input type="password" name="password">
						</td>
					</tr>
				</table>
				<input type="submit" value="로그인">
			</fieldset>
		</form>
	</section>
</body>
</html>
