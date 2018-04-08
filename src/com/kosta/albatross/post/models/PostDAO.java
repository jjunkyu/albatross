package com.kosta.albatross.post.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.kosta.albatross.DataSourceManager;
import com.kosta.albatross.PagingBean;
import com.kosta.albatross.member.models.MemberVO;


public class PostDAO {
	private DataSource dataSource;
	// 싱글톤
	private static PostDAO dao = new PostDAO();

	private PostDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static PostDAO getInstance() {
		return dao;
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public void closeAll(PreparedStatement pstmt, Connection con) {

		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 페이징을 처리하는 메소드
	 * 
	 * @param pagingBean
	 * @return list
	 * @throws SQLException
	 */
	public ArrayList<PostVO> getPostList(PagingBean pagingBean) throws SQLException {
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT p.pNo,p.title,p.timeposted,p.hits,p.id,m.name FROM( ");
			sql.append(" SELECT row_number() over(order by pNo desc) as rnum, pNo,title,hits, ");
			sql.append(" to_char(timeposted,'YYYY.MM.DD') as timeposted,id ");
			sql.append(" FROM semi_post ");
			sql.append(" ) p,semi_member m where p.id=m.id and rnum between ? and ? ");
			sql.append(" order by pNo desc ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagingBean.getStartRowNumber());
			pstmt.setInt(2, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostVO vo = new PostVO();
				vo.setpNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setTimePosted(rs.getString(3));
				vo.setHits(rs.getInt(4));
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString(5));
				mvo.setName(rs.getString(6));
				vo.setMemberVO(mvo);
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	/**
	 * 조회수 업데이트 하는 메소드
	 * 
	 * @param pNo
	 * @throws SQLException
	 */
	public void updateHit(int pNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "UPDATE SEMI_POST SET hits=hits+1 WHERE pNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	/**
	 * 총 게시물의 수를 체크하는 메서드
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getTotalPostCount() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM SEMI_POST";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
	/**
	 * My Account 상에서 내가 쓴 게시글의 목록을 볼때
	 * id로 검색한 게시글의 총 개수를 확인하는 메서드
	 * 
	 * @param id
	 * @return count
	 * @throws SQLException
	 */
	public int getTotalPostCountbyId(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM SEMI_POST WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
	/**
	 * 게시글의 번호로 상세정보를 반환받는 메소드
	 * 
	 * @param pNo
	 * @return pvo
	 * @throws SQLException
	 */
	public PostVO getPostDetail(int pNo) throws SQLException {
		PostVO pvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT p.title,to_char(p.timeposted,'YYYY.MM.DD  HH24:MI:SS') as timeposted ");
			sql.append(" ,p.content,p.hits,p.id,m.name ");
			sql.append(" FROM SEMI_POST p, SEMI_MEMBER m ");
			sql.append(" WHERE p.id=m.id AND p.pNo=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pvo = new PostVO();
				pvo.setpNo(pNo);
				pvo.setTitle(rs.getString("title"));
				pvo.setContent(rs.getString("content"));
				pvo.setHits(rs.getInt("hits"));
				pvo.setTimePosted(rs.getString("timeposted"));
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setName(rs.getString("name"));
				pvo.setMemberVO(mvo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return pvo;
	}
	/**
	 * 게시글을 지울 때 사용하는 메서드
	 * 
	 * @param pNo
	 * @throws SQLException
	 */
	public void deletePosting(int pNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=dataSource.getConnection();
			String sql="DELETE FROM SEMI_POST WHERE pNo=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	/**
	 * 글쓰기 DB 넣고 출력
	 * @param id
	 * @param title
	 * @param content
	 * @return
	 * @throws SQLException
	 */
	public PostVO posting(PostVO postVO) throws SQLException {
		PostVO resultVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO SEMI_POST(pNo,title,content,timeposted,id) ");
			sql.append(" VALUES(SEMI_POST_seq.nextval,?,?,sysdate,?) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, postVO.getTitle());
			pstmt.setString(2, postVO.getContent());
			pstmt.setString(3, postVO.getMemberVO().getId());
			pstmt.executeQuery();
			pstmt.close();
			
			String sql2="SELECT SEMI_POST_seq.currval FROM dual";
			pstmt=con.prepareStatement(sql2);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				resultVO = getPostDetail(rs.getInt(1));
			}
			
		}finally {
			closeAll(rs, pstmt, con);
		}
		return resultVO;
	}
	/**
	 * 게시글을 업데이트 즉 수정하는 메서드
	 * @param postVO
	 * @return pvo
	 * @throws SQLException
	 */
	public PostVO getPostUpdate(PostVO postVO) throws SQLException {
		PostVO pvo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql="UPDATE SEMI_POST SET title=? , content=? WHERE pNo=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, postVO.getTitle());
			pstmt.setString(2, postVO.getContent());
			pstmt.setInt(3, postVO.getpNo());
			pstmt.executeUpdate();
			pstmt.close();
			
			StringBuilder sql1 = new StringBuilder();
			sql1.append(" SELECT p.title,to_char(p.timeposted,'YYYY.MM.DD  HH24:MI:SS') as timeposted ");
			sql1.append(" ,p.content,p.hits,p.id,m.name ");
			sql1.append(" FROM SEMI_POST p, SEMI_MEMBER m ");
			sql1.append(" WHERE p.id=m.id AND p.pNo=? ");
			pstmt = con.prepareStatement(sql1.toString());
			pstmt.setInt(1, postVO.getpNo());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pvo = new PostVO();
				pvo.setpNo(postVO.getpNo());
				pvo.setTitle(rs.getString("title"));
				pvo.setContent(rs.getString("content"));
				pvo.setHits(rs.getInt("hits"));
				pvo.setTimePosted(rs.getString("timeposted"));
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setName(rs.getString("name"));
				pvo.setMemberVO(mvo);
			}
		} finally {
			closeAll(pstmt, con);
		}
		return pvo;
	}
	/**
	 * myAccount 에서 log in 한 사람이 자유게시판에서 쓴 글의 목록을 보여주는 메서드
	 * 목록에는 작성자 id, 글번호, 글 제목을 보여준다
	 * 목록은 ArrayList<PostVO> 의 list 란 변수로 받아온다
	 * sql 문은
	 *  SELECT pNo, id, title
		FROM SEMI_POST
		WHERE id = 'java'
	 * 
	 * @param id
	 * @return list
	 * @throws SQLException
	 */
	public ArrayList<PostVO> getMyPosting(String id,PagingBean pagingBean) throws SQLException {
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT p.pNo,p.title,p.timeposted,p.hits,p.id,m.name FROM( ");
			sql.append(" SELECT row_number() over(order by pNo desc) as rnum, pNo,title,hits, ");
			sql.append(" to_char(timeposted,'YYYY.MM.DD') as timeposted,id ");
			sql.append(" FROM SEMI_POST WHERE id = ?");
			sql.append(" ) p,SEMI_MEMBER m WHERE p.id=m.id AND rnum BETWEEN ? AND ? ");
			sql.append(" order by pNo desc ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostVO vo = new PostVO();
				vo.setpNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setTimePosted(rs.getString(3));
				vo.setHits(rs.getInt(4));
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString(5));
				mvo.setName(rs.getString(6));
				vo.setMemberVO(mvo);
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	
}
