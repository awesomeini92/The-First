package any_comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import any_comment.vo.ActionForward;

public interface Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
