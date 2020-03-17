package any_comment.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import any_comment.dao.CommentDAO;
import any_comment.vo.AnyCommentBean;

public class CommentListService {
	
	public ArrayList<AnyCommentBean> getCommentList(int post_num) {
		System.out.println("CommunityWriteService");
		ArrayList<AnyCommentBean> list = new ArrayList<AnyCommentBean>();
		
		Connection con = getConnection();
		CommentDAO cdao = CommentDAO.getInstance();
		cdao.setConnection(con);
		
		list = cdao.getCommentList(post_num);
		
		close(con);
		
		return list;
		
	}

}
