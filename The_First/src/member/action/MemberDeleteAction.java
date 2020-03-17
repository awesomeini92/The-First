package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.svc.MemberDeleteService;
import member.svc.MemberLoginService;
import member.vo.ActionForward;
import member.vo.MemberBean;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberDeleteAction!");
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		System.out.println("id = " + id + "pass = " + password);
		MemberBean member = new MemberBean(id,password);
		
		MemberLoginService memberLoginService = new MemberLoginService();
		int loginResult = memberLoginService.isLoginMember(member);
		
		if (loginResult == 1) {
			MemberDeleteService memberDeleteService = new MemberDeleteService();
			int deleteResult = memberDeleteService.deleteMember(id);
			
			if (deleteResult == 1) {
				HttpSession session = request.getSession();
				session.invalidate();
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter(); 
				out.println("<script>"); 
				out.println("alert('회원탈퇴 성공')"); 
				out.println("</script>"); 
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter(); 
				out.println("<script>"); 
				out.println("alert('회원탈퇴 실패)"); 
				out.println("history.back()"); 
				out.println("</script>"); 
			}
			
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); 
			out.println("alert('회원정보 불일치')"); 
			out.println("history.back()"); 
			out.println("</script>"); 
		}
		
		forward = new ActionForward();
		forward.setPath("");
		forward.setRedirect(true);
		
		return forward;
	}

}
