package any_comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import any_comment.svc.CommentWriteService;
import any_comment.vo.AnyCommentBean;
import any_comment.vo.ActionForward;

public class CommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommunityWriteProAction!");
		ActionForward forward = null;
		
		//파라미터 가져오기
		String nowPage = request.getParameter("nowPage");
		int post_num = Integer.parseInt(request.getParameter("comment_board"));
		String nickname = request.getParameter("comment_nickname");
		String comment = request.getParameter("comment_content");
		
		AnyCommentBean anyCommentBean = new AnyCommentBean(post_num, nickname, comment);
		
		CommentWriteService commentWriteService = new CommentWriteService();
		boolean isWriteSuccess = commentWriteService.writeComment(anyCommentBean);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('댓글 작성 실패')"); 
			out.println("history.back()"); 
			out.println("</script>"); 
		} else {
			forward = new ActionForward();
			forward.setPath("CommunityDetail.any?num=" + post_num + "&page=" + nowPage); 
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
