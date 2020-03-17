<%@page import="coding.vo.Coding_refBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="coding.vo.CodingBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete</title>
<%

	String id = null;
	// 로그인이 되지 않은 상태일 경우 로그인 페이지로 강제 이동 처리
	if(session.getAttribute("id") == null) {
		out.println("<script>");
	    out.println("alert('로그인이 필요한 서비스입니다!')");
	    out.println("location.href='LoginForm.me'");
	    out.println("</script>");
	} else { // 로그인 된 상태일 경우 세션 ID 가져오기
		id = (String)session.getAttribute("id");
	}
%>
<%
// CodingBean article = (CodingBean)request.getAttribute("article");
// ArrayList<Coding_refBean> article_refList = (ArrayList<Coding_refBean>)request.getAttribute("article_refList");
// String nowPage = (String)request.getAttribute("page");
int num = Integer.parseInt(request.getParameter("num"));
String nowPage = (String)request.getAttribute("page");
%>
<script type="text/javascript">
	var del = confirm("삭제하시겠습니까?")
	 if (del==true){    //확인
		 location.href="CodingDeletePro.code?num=<%=num%>&page=<%=nowPage%>";
		}else{   //취소
			history.back();
		}
	
</script>
</head>
<body>

</body>
</html>