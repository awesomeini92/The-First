package coding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import coding.svc.CodingDetailService;
import coding.vo.CodingBean;
import vo.ActionForward;

public class CodingModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CodingModifyFormAction");
		ActionForward forward = null;
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");
		
		// 원본 게시물을 가져오기 위해 별도의 Service 클래스를 새로 정의하지 않고
		CodingBean article = null;
		CodingDetailService codingDetailService = new CodingDetailService();
		article = codingDetailService.getArticle(num);
//		System.out.println(article.getBoard_content());
		
		// 원본 게시물과 페이지번호를 request 객체에 저장
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		
		forward = new ActionForward();
		forward.setPath("/coding/codingModify.jsp"); // Dispatch 방식으로 이동
		
		
		return forward;
	}

}
