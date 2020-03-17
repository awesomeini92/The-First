package coding.action;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import coding.svc.CodingWriteProService;
import coding.vo.CodingBean;
import vo.ActionForward;

public class CodingWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String saveFolder = "/codingUpload"; //가상위치 (이클립스가 생성한 폴더)
		ServletContext context2 = request.getServletContext();
		String realFolder = context2.getRealPath(saveFolder); //가상위치에 대한 실제파일주소
		
		int fileSize = 1024*1024*10; //10Mbyte
		
		// MultipartRequest 객체생성, cos.jar API 필수
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		//MultipartRequest 객체로부터 전달 된 파라미터들 가져오기
		String id = request.getParameter("id");
		String nickname = multi.getParameter("nickname");
		String subject = multi.getParameter("subject");
		String content =multi.getParameter("content");
//		int readcount = Integer.parseInt(multi.getParameter("readcount"));
		String file = multi.getOriginalFileName((String)multi.getFileNames().nextElement());
		int isPublic = Integer.parseInt(multi.getParameter("isPublic"));
//		int password = Integer.parseInt(multi.getParameter("password"));
		
		
		CodingBean codingBean = new CodingBean();
		codingBean.setNickname(nickname);
		codingBean.setSubject(subject);
		codingBean.setContent(content);
		codingBean.setFile(file);
//		codingBean.setReadcount(readcount);
		codingBean.setIsPublic(isPublic);
//		codingBean.setPassword(password);
		
		
		CodingWriteProService codingWriteProService = new CodingWriteProService();
		boolean isSuccess = codingWriteProService.insertArticle(codingBean);
		
		if(isSuccess) {
			forward = new ActionForward();
			forward.setPath("");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
