package coding.svc;

import java.sql.Connection;

import coding.dao.CodingDAO;
import coding.vo.CodingBean;
import db.JdbcUtil;

public class CodingWriteProService {

	public boolean insertArticle(CodingBean codingBean) {
		boolean isWriteSuccess = false; 
		
		Connection con = JdbcUtil.getConnetion();
		CodingDAO codingDAO = CodingDAO.getInstance();
		codingDAO.setConnection(con);
		
		int insertCount = codingDAO.insertCodingArticle(codingBean);
		if(insertCount>0) {
			JdbcUtil.commit(con);
			isWriteSuccess=true;
		}else {
			JdbcUtil.rollback(con);
		}
		
		return isWriteSuccess;
	}

}
