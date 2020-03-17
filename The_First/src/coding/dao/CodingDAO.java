package coding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import coding.vo.CmmntBean;
import coding.vo.CodingBean;
import coding.vo.Coding_refBean;

import static db.JdbcUtil.*;

public class CodingDAO {
	private CodingDAO() {};
	private static CodingDAO instance = new CodingDAO();
	public static CodingDAO getInstance() {
		return instance;
	};
	////////////////////////////////////////////////////////
	
	Connection con;

	public void setConnection(Connection con) { //외부에서 받은 Connection 객체를 전달받아 저장
		this.con = con;
	}

	//DB이름: coding_charge
	
	public int insertCodingArticle(CodingBean codingBean) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		
//		num | nickname  |	subject   | content  | readcount	| file 	| date |  isPublic 	| password
		String sql = "insert into coding_charge values(null,?,?,?,?,?,now(),?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, codingBean.getNickname());
			pstmt.setString(2, codingBean.getSubject());
			pstmt.setString(3, codingBean.getContent());
			pstmt.setInt(4, codingBean.getReadcount() );
			pstmt.setString(5, codingBean.getFile());
			pstmt.setInt(6, codingBean.getIsPublic()); //0 이면 무료, 1이면 유료
			pstmt.setInt(7, 0); //codingBean.getPassword()

			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	public int updateReadcount(int num) {
		// 게시물 조회 수 1 증가 후 결과(updateCount) 리턴
		// UPDATE 문을 사용하여 게시물 조회수(readcount) 를 1 증가시킴
		PreparedStatement pstmt = null;
		
		int updateCount = 0;
		
		// board_num 에 해당하는 board_readcount 값을 1 증가
		try {
			String sql = "UPDATE coding_charge SET readcount=readcount+1 WHERE num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public CodingBean selectArticle(int num) {
		CodingBean article = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 글번호(board_num)에 해당하는 게시물 정보 조회
		try {
			String sql = "SELECT * FROM coding_charge WHERE num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			// 게시물이 존재할 경우 BoardBean 객체에 저장
//			if(rs.next()) { //while?
			while(rs.next()) {
				article = new CodingBean();
				article.setNum(rs.getInt("num"));
				article.setNickname(rs.getString("nickname"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setReadcount(rs.getInt("readcount"));
				article.setFile(rs.getString("file"));
				article.setDate(rs.getDate("date"));
				article.setIsPublic(rs.getInt("isPublic"));
				article.setPassword(rs.getInt("password"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return article;
	}
	public int selectListCount() {
		// 총 게시물 수 조회하여 리턴
//		System.out.println("BoardDAO - selectListCount()");
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM coding_charge";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<CodingBean> selectArticleList(int page, int limit) { // int page, int limit
		// 게시물 목록 조회 후 리턴
		ArrayList<CodingBean> articleList = new ArrayList<CodingBean>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int startRow = (page - 1) * 10;
		/* 
		 * 전체 게시물 중 원하는 페이지의 게시물 첫번째 row 번호 설정
		 * - 원본 글 번호(board_re_ref) 기준으로 내림차순 정렬
		 * - 글 순서번호(board_re_seq) 기준으로 오름차순 정렬
		 * - 조회할 게시물 갯수 : 첫번째 게시물 위치 ~ limit 수 만큼
		 *   첫번째 게시물 위치 = (현재페이지 - 1) * 10
		 * 
		 * ex) 현재 페이지(page) 가 1 페이지 일 경우 : 게시물 조회 결과의 0번 행부터 10개 가져오기
		 */
//		int startRow = (page - 1) * 10; // 첫번째 게시물 행(row) 번호 계산
		
		try {
			// 조회 결과(ResultSet) 객체가 존재할 경우
			// 반복문을 사용하여 1개 게시물 정보(패스워드 제외한 나머지)를 BoardBean 객체에 저장하고
			// BoardBean 객체를 ArrayList<BoardBean> 객체에 저장 반복
//			String sql = "SELECT * FROM board ORDER BY board_re_ref DESC, board_re_seq ASC LIMIT ?,?";
			String sql = "SELECT * FROM coding_charge LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, startRow);
	          pstmt.setInt(2, limit);
            rs = pstmt.executeQuery();
            
            // ResultSet 객체 내의 모든 레코드를 각각 레코드별로 BoardBean 에 담아서 ArrayList 객체에 저장
            // => 패스워드 제외
            while(rs.next()) {
            	CodingBean codingBean = new CodingBean();
            	codingBean.setNum(rs.getInt("num"));
            	codingBean.setNickname(rs.getString("nickname"));
            	codingBean.setSubject(rs.getString("subject"));
            	codingBean.setContent(rs.getString("content"));
            	codingBean.setReadcount(rs.getInt("readcount"));
            	codingBean.setFile(rs.getString("file"));
            	codingBean.setDate(rs.getDate("date"));
            	codingBean.setIsPublic(rs.getInt("isPublic"));
            	codingBean.setPassword(rs.getInt("password"));
                
                articleList.add(codingBean);
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}

	public int updateArticle(CodingBean article) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
//			String sql = "UPDATE board SET board_name=?,board_subject=?,board_content=? WHERE board_num=?";
			String sql = "UPDATE coding_charge SET subject=?, content=? WHERE num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getSubject());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getNum());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public int insertCodingArticle_ref(Coding_refBean coding_refBean) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		
//		ref_num | post_num | nickname  |	subject   | content  | readcount	| file 	| date |  isSelected 
		String sql = "insert into coding_charge_ref values(null,?,?,?,?,?,?,now(),0)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, coding_refBean.getPost_num());
			pstmt.setString(2, coding_refBean.getNickname());
			pstmt.setString(3, coding_refBean.getSubject());
			pstmt.setString(4, coding_refBean.getContent());
			pstmt.setInt(5, coding_refBean.getReadcount() );
			pstmt.setString(6, coding_refBean.getFile());
//			pstmt.setInt(7, coding_refBean.getIsSelected()); //0 이면 무료, 1이면 유료

			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	public Coding_refBean selectArticle_ref(int num) {
		Coding_refBean article_ref = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 글번호(board_num)에 해당하는 게시물 정보 조회
		try {
			String sql = "SELECT * FROM coding_charge_ref WHERE post_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			// 게시물이 존재할 경우 BoardBean 객체에 저장
//			if(rs.next()) { //while?
			while(rs.next()) {
				article_ref = new Coding_refBean();
				article_ref.setRef_num(rs.getInt("ref_num"));
				article_ref.setPost_num(rs.getInt("post_num"));
				article_ref.setNickname(rs.getString("nickname"));
				article_ref.setSubject(rs.getString("subject"));
				article_ref.setContent(rs.getString("content"));
				article_ref.setReadcount(rs.getInt("readcount"));
				article_ref.setFile(rs.getString("file"));
				article_ref.setDate(rs.getDate("date"));
				article_ref.setIsSelected(rs.getInt("isSelected"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return article_ref;
	}

	public ArrayList<Coding_refBean> selectArticleReplyList(int num) {
		ArrayList<Coding_refBean> article_refList = new ArrayList<Coding_refBean>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM coding_charge_ref WHERE post_num=?";
			pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	Coding_refBean coding_refBean = new Coding_refBean();
            	coding_refBean.setRef_num(rs.getInt("ref_num"));
            	coding_refBean.setPost_num(rs.getInt("post_num"));
            	coding_refBean.setNickname(rs.getString("nickname"));
            	coding_refBean.setSubject(rs.getString("subject"));
            	coding_refBean.setContent(rs.getString("content"));
            	coding_refBean.setReadcount(rs.getInt("readcount"));
            	coding_refBean.setFile(rs.getString("file"));
            	coding_refBean.setDate(rs.getDate("date"));
            	coding_refBean.setIsSelected(rs.getInt("isSelected"));
                
                article_refList.add(coding_refBean);
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return article_refList;
	}

	public int deleteArticle(int num) {
		int deleteCount = 0 ;
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM coding_charge_ref WHERE post_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
//			deleteCount = pstmt.executeUpdate();
//			if(deleteCount>0) {
				sql = "DELETE FROM coding_charge WHERE num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				deleteCount = pstmt.executeUpdate();
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	public int insertCmmnt(CmmntBean cmmntBean) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		
		String sql = "insert into coding_charge_comment values(null,?,?,?,now())";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cmmntBean.getPost_num());
			pstmt.setString(2, cmmntBean.getNickname());
			pstmt.setString(3, cmmntBean.getComment());

			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	public int selectCommentListCount(int num) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM coding_charge_comment WHERE post_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	
	public ArrayList<CmmntBean> selectCmmntList(int num, int cmmnt_page) {
		ArrayList<CmmntBean> cmmntfList = new ArrayList<CmmntBean>();
		int startRow = (cmmnt_page - 1) * 10;
		int limit = 10;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT * FROM coding_charge_comment WHERE post_num=? LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, num);
			 pstmt.setInt(2, startRow);
	         pstmt.setInt(3, limit);
			
            
			 rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	CmmntBean cmmntBean = new CmmntBean();
            	cmmntBean.setComment_num(rs.getInt("comment_num"));
            	cmmntBean.setPost_num(rs.getInt("post_num"));
            	cmmntBean.setNickname(rs.getString("nickname"));
            	cmmntBean.setComment(rs.getString("comment"));
            	cmmntBean.setDate(rs.getDate("date"));
                
            	cmmntfList.add(cmmntBean);
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return cmmntfList;
	}

	public int updateCmmnt(CmmntBean cmmntBean) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE coding_charge_comment SET comment=?, date=now() WHERE comment_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cmmntBean.getComment());
			pstmt.setInt(2, cmmntBean.getComment_num());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	
	public int deleteCmmnt(int comment_num) {
		int deleteCount = 0 ;
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM coding_charge_comment WHERE comment_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_num);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	
}
