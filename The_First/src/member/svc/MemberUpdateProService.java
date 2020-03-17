package member.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

public class MemberUpdateProService {

	public boolean updateMember(MemberBean mb) {
		System.out.println("MemberUpdateProService");
		boolean isUpdateSuccess = false;
		int updateCount = 0;

		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		updateCount = memberDAO.updateMember(mb);
		
		if (updateCount > 0) {
			commit(con);
			isUpdateSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isUpdateSuccess;
	}
}
