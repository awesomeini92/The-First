package any_community.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import any_community.svc.CommunityDeleteProService;
import any_community.vo.ActionForward;

public class CommunityDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityDeleteProAction");
		ActionForward forward = null;
		
		int num = Integer.parseInt(request.getParameter("num"));
		String nowPage = request.getParameter("page");
		
		CommunityDeleteProService communityDeleteProService = new CommunityDeleteProService();
		boolean isDeleteSuccess = communityDeleteProService.deleteArticle(num);
		
		if (!isDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("CommunityList.any?page=" + nowPage);
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
