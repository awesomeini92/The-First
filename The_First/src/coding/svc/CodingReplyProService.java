package coding.svc;

import java.sql.Connection;

import coding.dao.CodingDAO;
import coding.vo.Coding_refBean;
import static db.JdbcUtil.*;

public class CodingReplyProService {

	public boolean insertArticle_ref(Coding_refBean coding_refBean) {
		boolean isSuccess = false;
		Connection con = getConnection();
		CodingDAO codingDAO = CodingDAO.getInstance();
		codingDAO.setConnection(con);
		
		int insertCount = codingDAO.insertCodingArticle_ref(coding_refBean);
		if(insertCount>0) {
			commit(con);
			isSuccess=true;
		}else {
			rollback(con);
		}
		
		
		return isSuccess;
	}

}
