package kosta.albatross.post.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import kosta.albatross.common.models.DataSourceManager;
import kosta.albatross.common.models.PagingBean;
import kosta.albatross.member.models.MemberVO;


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
	 * @return
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
			String sql = "update semi_post set hits=hits+1 where pNo=?";
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
			String sql = "select count(*) from semi_post";
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
	 * 게시글의 번호로 상세정보를 반환받는 메소드
	 * 
	 * @param pNo
	 * @return
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
			sql.append(" select p.title,to_char(p.timeposted,'YYYY.MM.DD  HH24:MI:SS') as timeposted ");
			sql.append(" ,p.content,p.hits,p.id,m.name ");
			sql.append(" from semi_post p, semi_member m ");
			sql.append(" where p.id=m.id and p.pNo=? ");
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

	public void deletePosting(int pNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=dataSource.getConnection();
			String sql="delete from semi_post where pNo=?";
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
			String sql = "insert into semi_post(pNo,title,content,timeposted,id)" + 
					" values(semi_post_seq.nextval,?,?,sysdate,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, postVO.getTitle());
			pstmt.setString(2, postVO.getContent());
			pstmt.setString(3, postVO.getMemberVO().getId());
			pstmt.executeQuery();
			pstmt.close();
			String sql2="select semi_post_seq.currval from dual";
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

	public PostVO getPostUpdate(PostVO postVO) throws SQLException {
		PostVO pvo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql="update  semi_post set title=? , content=? where pNo=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, postVO.getTitle());
			pstmt.setString(2, postVO.getContent());
			pstmt.setInt(3, postVO.getpNo());
			pstmt.executeUpdate();
			pstmt.close();
			
			StringBuilder sql1 = new StringBuilder();
			sql1.append(" select p.title,to_char(p.timeposted,'YYYY.MM.DD  HH24:MI:SS') as timeposted ");
			sql1.append(" ,p.content,p.hits,p.id,m.name ");
			sql1.append(" from semi_post p, semi_member m ");
			sql1.append(" where p.id=m.id and p.pNo=? ");
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
}
