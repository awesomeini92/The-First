package coding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import coding.svc.CmmntDeleteProService;
import coding.svc.CodingDeleteProService;
import vo.ActionForward;

public class CmmntDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("CmmntDeleteProAction");
		
		int num = Integer.parseInt(request.getParameter("post_num"));
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		String page = request.getParameter("page");
		
		CmmntDeleteProService cmmntDeleteProService = new CmmntDeleteProService();
		boolean isDelete = cmmntDeleteProService.deleteCmmnt(comment_num);
		 
		
		if(isDelete) {
			forward = new ActionForward();
			request.setAttribute("num", num);
			request.setAttribute("page", page);
//			forward.setPath("/coding/codingView.jsp");
			forward.setPath("CodingDetail.code");
			forward.setRedirect(true);
		}
		return forward;
	}

}
