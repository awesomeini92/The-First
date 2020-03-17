package coding.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import coding.svc.CodingDeleteProService;
import vo.ActionForward;

public class CodingDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");
		
		// 게시물 삭제를 위한 본인 확인 작업 => BoardDeleteProService 클래스의 isArticleWriter() 메서드 호출
		// => 파라미터 : 글번호(board_num) & 패스워드(board_pass), 리턴타입 : boolean(isRightUser)
		CodingDeleteProService codingDeleteProService = new CodingDeleteProService();
//		boolean isRightUser = codingDeleteProService.isArticleWriter(num, request.getParameter("board_pass"));
		
		boolean isRightUser = true;
		
		// 리턴된 isRightUser 가 false 일 경우
		// 자바스크립트를 사용하여 "삭제 권한이 없습니다!" 출력 후 이전 페이지로 이동
		// 아니면 "삭제 완료!" 출력
		if(!isRightUser) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
//			System.out.println("삭제 완료!");
			// BoardDeleteProService 클래스의 removeArticle() 메서드를 호출하여 삭제 작업 수행
			// => 파라미터 : 글번호(board_num)    리턴타입 : boolean(isDeleteSuccess)
			boolean isDeleteSuccess = codingDeleteProService.removeArticle(num);
			
			// isDeleteSuccess 가 false 일 경우
			// => 자바스크립트를 사용하여 삭제 실패! 출력 후 이전페이지로 돌아가기
			// isDeleteSuccess 가 true 일 경우
			// => ActionForward 객체를 사용하여 글목록(BoardList.bo) 페이지로 이동(파라미터 page 전달)
			if(!isDeleteSuccess) {
				// 리턴받은 수정 결과(isModifySuccess)가 false 일 경우
				// => 자바스크립트를 사용하여 "글 수정 실패!" 출력 후 이전 페이지로 이동
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('글 삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				 
				forward = new ActionForward();
				request.setAttribute("page", page);
				forward.setPath("/coding/codingList.jsp");
				forward.setRedirect(true);
			}
			
		}
		
		return forward;
	}

}
