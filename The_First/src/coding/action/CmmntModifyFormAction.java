package coding.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import coding.svc.CmmntListService;
import coding.svc.CodingDetailService;
import coding.svc.CodingReplyListService;
import coding.vo.CmmntBean;
import coding.vo.CodingBean;
import coding.vo.Coding_refBean;
import vo.ActionForward;

public class CmmntModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int num = Integer.parseInt(request.getParameter("post_num"));
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		
		CodingDetailService codingDetailService = new CodingDetailService();
		CodingBean article = codingDetailService.getArticle(num);
		
		CodingReplyListService codingReplyListService = new CodingReplyListService();
		ArrayList<Coding_refBean>article_refList = codingReplyListService.getReplyList(num);
		
		CmmntListService cmmntListService = new CmmntListService();
		int cmmnt_count = cmmntListService.getCommentListCount(comment_num);
		ArrayList<CmmntBean> cmmntList = cmmntListService.getCmmntList(num,cmmnt_count);
		
		// 리턴받은 BoardBean 객체가 null 이 아닐 경우 BoardDetailService 클래스의 plusReadcount() 메서드 호출
		if(article != null) {
			codingDetailService.updateReadcount(num);
		}
		
		// 게시물 정보(BoardBean 객체), 페이지번호(page) 를 request 객체에 저장
		request.setAttribute("article", article);
//		request.setAttribute("comment_num", comment_num);
		request.setAttribute("article_refList", article_refList);
		request.setAttribute("cmmntList", cmmntList);
		
		
		forward = new ActionForward();
		forward.setPath("/coding/cmmntUpdateForm.jsp");
		
		return forward;
	}

}
