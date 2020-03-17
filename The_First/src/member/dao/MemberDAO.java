package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.vo.MemberBean;
import member.vo.SHA256;

import static db.JdbcUtil.*;

public class MemberDAO {
	private MemberDAO() {}
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	Connection con = null;
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	// 로그인
	public int selectLoginMember(MemberBean member) {
		int loginResult = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT password FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				if(rs.getString(1).equals(member.getPassword())) {
					loginResult = 1;
				} else { 
					loginResult = -1;
				}
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return loginResult;
	}

	// 회원가입
	public int insertMember(MemberBean member) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getNickname());
			pstmt.setString(4, member.getEmail());
			pstmt.setInt(5, 0); // CP
			pstmt.setInt(6, 1); // level
			pstmt.setString(7, SHA256.getSHA256(member.getEmail())); // email 인증
			pstmt.setInt(8, 0); // false
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	
	// 회원 가져오기
	public MemberBean getMember(String id) {
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean mb = new MemberBean();
		
		try {
			String sql = "SELECT * FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				mb.setId(rs.getString("id"));
				mb.setNickname(rs.getString("nickname"));
				mb.setEmail(rs.getString("email"));
				mb.setCp(rs.getInt("cp"));
				mb.setLevel(rs.getInt("level"));
				mb.setDate(rs.getDate("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return mb;
	}
	
	// 정보수정
	public int updateMember(MemberBean mb) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			String sql = "UPDATE member SET password=?,nickname=?,email=? WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getPassword());
			pstmt.setString(2, mb.getNickname());
			pstmt.setString(3, mb.getEmail());
			pstmt.setString(4, mb.getId());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	
	// 닉네임
	public String getUserNickname(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT nickname FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				return rs.getString(1); // 닉네임 반환
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return null; // 데이터베이스 오류
	}
	
	// 
	public String getUserEmail(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT email FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				return rs.getString(1); // 이메일 주소 반환
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return null; // 데이터베이스 오류
	}
	
	public boolean getUserEmailChecked(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT emailChecked FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getBoolean(1); // 이메일 등록 여부 반환
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return false; // 데이터베이스 오류
	}

	public boolean setUserEmailChecked(String id) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE member SET emailChecked = true WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return false;
	}

	public int deleteMember(String id) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			String sql = "DELETE FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	
	
}
























