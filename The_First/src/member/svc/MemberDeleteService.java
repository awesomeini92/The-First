package member.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import member.dao.MemberDAO;

public class MemberDeleteService {

	public int deleteMember(String id) {
		System.out.println("MemberDeleteService");
		int deleteResult = 0;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		deleteResult = memberDAO.deleteMember(id);
		
		close(con);
		
		return deleteResult;
	}

}
