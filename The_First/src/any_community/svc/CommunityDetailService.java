package any_community.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import any_community.dao.CommunityDAO;
import any_community.vo.CommunityBean;

public class CommunityDetailService {

	public CommunityBean getArticle(int num) {
		System.out.println("CommunityDetailService");

		Connection con = getConnection();
		CommunityDAO cdao = CommunityDAO.getInstance();
		cdao.setConnection(con);
		
		CommunityBean article = null;
		
		article = cdao.selectArticle(num);
		
		close(con);
		
		return article;
	}

}
