package shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import shop.action.ShopListAction;
import shop.action.ShopRegistProAction;
import vo.ActionForward;

@WebServlet("*.shop")
public class ShopFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ShopFrontController!");
		
		//post방식 요청에 대한 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 요청된 서블릿 주소를 바로 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// 공통된 작업 처리를 위한 Action, ActionForward 타입 변수 선언
		Action action = null;
		ActionForward forward = null;
		
		
		if(command.equals("/ShopList.shop")) {
			action = new ShopListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/ShopRegistForm.shop")) {
			forward = new ActionForward();
			forward.setPath("/shop/shop_regist.jsp");
			
		}else if(command.equals("/ShopRegistPro.shop")) {
			action = new ShopRegistProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		

		
		if(forward != null) {
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
		doProcess(request,response);
	}

}
