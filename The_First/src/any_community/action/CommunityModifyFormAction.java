package any_community.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import any_community.svc.CommunityDetailService;
import any_community.vo.ActionForward;
import any_community.vo.CommunityBean;

public class CommunityModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityModifyFormAction");

		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");
		
		CommunityBean article = null;
		CommunityDetailService communityDetailService = new CommunityDetailService();
		article = communityDetailService.getArticle(num);
		
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/any_community/communityModify.jsp");
		
		return forward;
	}

}
