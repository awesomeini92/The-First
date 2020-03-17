<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CodingWriteForm</title>
<style type="text/css">
#registForm{
	width:500px;
	height:610px;
	border: 1px solid red;
	margin: auto;	
}

h2{text-align:center;}

table{margin: auto; width: 450px;}

.td_left{ width: 150px; background: orange;}
.td_right{ width: 300px; background: skyblue;}

#commandCell{text-align: center;}
</style>




</head>

<body>
<h1>글쓰기</h1>
<section id ="writeForm">
<h2>게시판등록</h2>
<form action="CodingWirtePro.code" method="post" enctype="multipart/form-data" name="boardform">
<table>
<tr>
	<td class="td_left"><label for="nickname">글쓴이</label></td>
	<td class="td_right"><input type="text" name="nickname" id="nickname" /></td>
</tr>
<tr>
	<td class="td_left"><label for="isPublic">무료0유료1</label></td>
	<td class="td_right"><input type="text" name="isPublic" id="isPublic" required="required" /></td>
</tr>
<tr>
	<td class="td_left"><label for="subject">제목</label></td>
	<td class="td_right"><input type="text" name="subject" id="subject" required="required" /></td>
</tr>
<tr>
	<td class="td_left"><label for="code_file">파일 첨부</label></td>
	<td class="td_right"><input name="code_file"  type="file"  id="code_file" required="required" /></td>
</tr>
<tr>
	<td class="td_left"><label for="content">내용</label></td>
	<td class="td_right"><textarea name="content" id="content" cols="40" rows="15" required="required" ></textarea></td>
</tr>
</table>
	<section id="commandCell">
	<input type="submit" value="등록">&nbsp;&nbsp;
	<input type="reset" value="다시쓰기" />
	</section>
</form>
</section>
</body>
</html>