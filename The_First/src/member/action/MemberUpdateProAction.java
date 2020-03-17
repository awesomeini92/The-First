package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.svc.MemberUpdateProService;
import member.vo.ActionForward;
import member.vo.MemberBean;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdateProAction");
		ActionForward forward = null;

		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		MemberBean mb = new MemberBean(id, password, nickname, email);
		
		MemberUpdateProService memberUpdateProService = new MemberUpdateProService();
		boolean isUpdateSuccess = memberUpdateProService.updateMember(mb);
		
		if(!isUpdateSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}


