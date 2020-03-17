package any_community.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import any_community.svc.CommunityListService;
import any_community.vo.ActionForward;
import any_community.vo.CommunityBean;
import any_community.vo.PageInfo;

public class CommunityListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityListAction!");
		ActionForward forward = null;
		
		int page = 1;
		int limit = 10;
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		CommunityListService communityListService = new CommunityListService();
		int listCount = communityListService.getListCount();
		
		ArrayList<CommunityBean> articleList = communityListService.getArticleList(page, limit);
		
		int maxPage = (int)((double)listCount / limit + 0.95);
		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		forward = new ActionForward();
		forward.setPath("/any_community/communityList.jsp");
		
		return forward;
	}

}
