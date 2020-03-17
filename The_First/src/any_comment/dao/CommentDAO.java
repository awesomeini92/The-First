package any_comment.dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import any_comment.vo.AnyCommentBean;

public class CommentDAO {

	private CommentDAO() {}
	private static CommentDAO instance = new CommentDAO();
	public static CommentDAO getInstance() {
		return instance;
	}
	
	Connection con = null;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	
	// 댓글작성
	public int writeComment(AnyCommentBean anyCommentBean) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int insertCount = 0;
		int num = 0;
		
		try {
			String sql = "SELECT max(comment_num) as mnum FROM any_comment";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				num = rs.getInt("mnum") + 1;
			}
			
		    sql = "INSERT INTO any_comment VALUES (?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, anyCommentBean.getPost_num());
			pstmt.setString(3, anyCommentBean.getNickname());
			pstmt.setString(4, anyCommentBean.getComment());
			
			insertCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return insertCount;
	}
	
	// 댓글 목록 가져오기
	public ArrayList<AnyCommentBean> getCommentList(int post_num) {
		ArrayList<AnyCommentBean> list = new ArrayList<AnyCommentBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM any_comment where post_num = ? ORDER BY comment_num";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				AnyCommentBean anyCommentBean = new AnyCommentBean();
				anyCommentBean.setComment_num(rs.getInt("comment_num"));
				anyCommentBean.setPost_num(rs.getInt("post_num"));
				anyCommentBean.setNickname(rs.getString("nickname"));
				anyCommentBean.setComment(rs.getString("comment"));
				anyCommentBean.setDate(rs.getDate("date"));
				list.add(anyCommentBean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return list;
	}
	
	
}
