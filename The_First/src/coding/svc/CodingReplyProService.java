package coding.svc;

import java.sql.Connection;

import coding.dao.CodingDAO;
import coding.vo.Coding_refBean;
import db.JdbcUtil;

public class CodingReplyProService {

	public boolean insertArticle_ref(Coding_refBean coding_refBean) {
		boolean isSuccess = false;
		Connection con = JdbcUtil.getConnetion();
		CodingDAO codingDAO = CodingDAO.getInstance();
		codingDAO.setConnection(con);
		
		int insertCount = codingDAO.insertCodingArticle_ref(coding_refBean);
		if(insertCount>0) {
			JdbcUtil.commit(con);
			isSuccess=true;
		}else {
			JdbcUtil.rollback(con);
		}
		
		
		return isSuccess;
	}

}
