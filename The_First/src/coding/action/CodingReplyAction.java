package coding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import coding.svc.CodingDetailService;
import coding.vo.CodingBean;
import vo.ActionForward;

public class CodingReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int num = Integer.parseInt(request.getParameter("post_num"));
		
		CodingBean article = null;
		CodingDetailService codingDetailService = new CodingDetailService();
		article = codingDetailService.getArticle(num);
//		System.out.println(article.getBoard_content());
		
		// 원본 게시물과 페이지번호를 request 객체에 저장
		request.setAttribute("article", article);
		
		
		forward = new ActionForward();
		forward.setPath("/coding/codingReply.jsp");
		
		return forward;
	}

}
