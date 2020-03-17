package coding.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnetion;

import java.sql.Connection;
import java.util.ArrayList;

import coding.dao.CodingDAO;
import coding.vo.CmmntBean;

public class CmmntListService {

	public int getCommentListCount(int num) {
		int listCount = 0;
		Connection con = getConnetion();
		CodingDAO codingDAO = CodingDAO.getInstance();
		codingDAO.setConnection(con);
		
		listCount = codingDAO.selectCommentListCount(num);
		
		close(con);
		
		return listCount;
	}
	
	public ArrayList<CmmntBean> getCmmntList(int num, int cmmnt_page) {
		ArrayList<CmmntBean> cmmntList = null;
		
		Connection con = getConnetion();
		CodingDAO codingDAO = CodingDAO.getInstance();
		codingDAO.setConnection(con);
		
		cmmntList = codingDAO.selectCmmntList(num, cmmnt_page);
		
		return cmmntList;
	}

}
