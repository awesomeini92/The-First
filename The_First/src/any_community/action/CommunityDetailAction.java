package any_community.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import any_comment.svc.CommentListService;
import any_comment.vo.AnyCommentBean;
import any_community.svc.CommunityDetailService;
import any_community.vo.ActionForward;
import any_community.vo.CommunityBean;

public class CommunityDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityDetailAction!");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");
		
		CommunityDetailService communityDetailService = new CommunityDetailService();
		CommunityBean article = communityDetailService.getArticle(num);
		
		CommentListService commentListService = new CommentListService();
		ArrayList<AnyCommentBean> comentList = commentListService.getCommentList(num);
		if (comentList.size() > 0) {
			request.setAttribute("commentList", comentList);
		}
		
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/any_community/communityView.jsp");
		
		return forward;
	}

}
