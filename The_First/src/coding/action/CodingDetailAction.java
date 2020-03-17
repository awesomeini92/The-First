package coding.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import coding.svc.CmmntListService;
import coding.svc.CodingDetailService;
import coding.svc.CodingListService;
import coding.svc.CodingReplyListService;
import coding.vo.CmmntBean;
import coding.vo.CodingBean;
import coding.vo.Coding_refBean;
import coding.vo.PageInfo;
import vo.ActionForward;

public class CodingDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page"); 
		int cmmnt_page = 1; // 현재 페이지 번호
		int cmmnt_limit = 10; // 한 페이지 당 출력할 댓글 수
		
		System.out.println(page);
		
		CodingDetailService codingDetailService = new CodingDetailService();
		CodingBean article = codingDetailService.getArticle(num);
		
		CodingReplyListService codingReplyListService = new CodingReplyListService();
		ArrayList<Coding_refBean>article_refList = codingReplyListService.getReplyList(num);
		
		CmmntListService cmmntListService = new CmmntListService();
		int cmmnt_count = cmmntListService.getCommentListCount(num);
		ArrayList<CmmntBean> cmmntList = cmmntListService.getCmmntList(num, cmmnt_page);
		
		// 리턴받은 BoardBean 객체가 null 이 아닐 경우 BoardDetailService 클래스의 plusReadcount() 메서드 호출
		if(article != null) {
			codingDetailService.updateReadcount(num);
		}
		

		//댓글페이지 계산
		// 1. 총 페이지 수 계산
		int cmmnt_maxPage = (int)((double)cmmnt_page / cmmnt_limit + 0.95);
		// 2. 시작 페이지 번호 계산
		int cmmnt_startPage = (((int)((double)cmmnt_page / 10 + 0.9)) - 1) * 10 + 1;
		// 3. 마지막 페이지 번호 계산
		int cmmnt_endPage = cmmnt_startPage + 10 - 1;
		
		// 마지막 페이지 번호가 총 페이지 수 보다 클 경우 총 페이지 수를 마지막 페이지 번호로 설정
		if(cmmnt_endPage > cmmnt_maxPage) {
				cmmnt_endPage = cmmnt_maxPage;
		}
				
		// PageInfo 객체에 페이지 정보 저장
		PageInfo cmmnt_pageInfo = new PageInfo(cmmnt_page, cmmnt_maxPage, cmmnt_startPage, cmmnt_endPage, cmmnt_count);

		
		request.setAttribute("cmmnt_pageInfo", cmmnt_pageInfo);
//		request.setAttribute("cmmnt_page", cmmnt_page);
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		request.setAttribute("article_refList", article_refList);
		request.setAttribute("cmmntList", cmmntList);
		
		
		forward = new ActionForward();
		forward.setPath("/coding/codingView.jsp");
		
		return forward;
	}

}
