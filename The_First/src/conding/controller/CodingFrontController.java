package conding.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import coding.action.CmmntDeleteProAction;
import coding.action.CmmntModifyFormAction;
import coding.action.CmmntModifyProAction;
import coding.action.CmmntWriteProAction;
import coding.action.CodingDeleteFormAction;
import coding.action.CodingDeleteProAction;
import coding.action.CodingDetailAction;
import coding.action.CodingListAction;
import coding.action.CodingModifyFormAction;
import coding.action.CodingModifyProAction;
import coding.action.CodingReplyAction;
import coding.action.CodingReplyProAction;
import coding.action.CodingWriteProAction;
import vo.ActionForward;

@WebServlet("*.code")
public class CodingFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getServletPath();
		
		Action action = null;
		ActionForward forward = null;
		
		System.out.println(".code 페이지 이동");
		
		if(command.equals("/CodingList.code")) {
			action = new CodingListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CodingWriteForm.code")) {
			forward = new ActionForward();
			forward.setPath("/coding/codingWriteForm.jsp");
		}else if(command.equals("/CodingWirtePro.code")) {
			action = new CodingWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CodingDetail.code")) {
			action = new CodingDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CodingModifyForm.code")) {
			action = new CodingModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CodingModifyPro.code")) {
			action = new CodingModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/CodeFileDown.code")) {
			forward = new ActionForward();
			forward.setPath("/coding/file_down.jsp");
		}else if(command.equals("/CodingReply.code")) {
			action = new CodingReplyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CodingReplyPro.code")) {
			action = new CodingReplyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CodingDeleteForm.code")) {
				action = new CodingDeleteFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/CodingDeletePro.code")) {
			action = new CodingDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CmmntWritePro.code")) {
			action = new CmmntWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CmmntUpdateForm.code")) {
			action = new CmmntModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CmmntUpdatePro.code")) {
			action = new CmmntModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CmmntDeletePro.code")) {
			action = new CmmntDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
		
		
		
		
		
		// 이동 설정
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else { 
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}else {
			System.out.println("ActionForward 객체 값이 null 입니다.");
		}
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
}
