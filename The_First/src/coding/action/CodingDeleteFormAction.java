package coding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import coding.svc.CodingDetailService;
import coding.vo.CodingBean;
import vo.ActionForward;

public class CodingDeleteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CodingDeleteFormAction");
		ActionForward forward = null;
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");
		
		// 원본 게시물과 페이지번호를 request 객체에 저장
		request.setAttribute("num", num);
		request.setAttribute("page", page);
		
		forward = new ActionForward();
		forward.setPath("/coding/codingDelete.jsp"); // Dispatch 방식으로 이동
		
		
		return forward;
	}

}
