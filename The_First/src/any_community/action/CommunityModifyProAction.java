package any_community.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import any_community.svc.CommunityModifyService;
import any_community.vo.ActionForward;
import any_community.vo.CommunityBean;

public class CommunityModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityModifyProAction");

		ActionForward forward = null;
		
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");
		
		CommunityModifyService communityModifyService = new CommunityModifyService();
		
			CommunityBean article = new CommunityBean();
			article.setNum(num);
			article.setSubject(request.getParameter("subject"));
			article.setContent(request.getParameter("content"));
			
			boolean isModifySuccess = communityModifyService.modifyArticle(article);
	
			if(!isModifySuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('글 수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("CommunityDetail.any?num=" + num + "&page=" + page);
				forward.setRedirect(true);
			}
		
		return forward;
	}

}
