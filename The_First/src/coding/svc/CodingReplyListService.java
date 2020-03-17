package coding.svc;

import java.sql.Connection;
import java.util.ArrayList;
import static db.JdbcUtil.*;

import coding.dao.CodingDAO;
import coding.vo.CodingBean;
import coding.vo.Coding_refBean;

public class CodingReplyListService {

	public ArrayList<Coding_refBean> getReplyList(int num) {
		ArrayList<Coding_refBean> article_refList = null;
		Connection con = getConnetion();
		CodingDAO codingDAO = CodingDAO.getInstance();
		codingDAO.setConnection(con);
		
		article_refList = codingDAO.selectArticleReplyList(num);
		
		close(con);
		
		return article_refList;
	}
	public int getReplyListCount() {
		int listCount = 0;
		Connection con = getConnetion();
		CodingDAO codingDAO = CodingDAO.getInstance();
		codingDAO.setConnection(con);
		
//		listCount = codingDAO.selectReplyListCount();
		
		close(con);
		
		return listCount;
	}

	
	public ArrayList<Coding_refBean> getArticleReplyList(int num) {
		ArrayList<Coding_refBean> article_refList = null;
		Connection con = getConnetion();
		CodingDAO codingDAO = CodingDAO.getInstance();
		codingDAO.setConnection(con);
		
		article_refList = codingDAO.selectArticleReplyList(num);
		
		close(con);
		
		return article_refList;
	}

}
