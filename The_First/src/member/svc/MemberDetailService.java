package member.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

public class MemberDetailService {

	public MemberBean getMember(String id) {
		System.out.println("MemberDetailService");

		MemberBean mb = null;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
			
		mb = memberDAO.getMember(id);
		
		close(con);
		
		return mb;
	}
}
