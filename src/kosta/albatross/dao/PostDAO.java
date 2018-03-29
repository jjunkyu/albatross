package kosta.albatross.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import kosta.albatross.vo.PostVO;

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

	public ArrayList<PostVO> getPostList(PagingBean pagingBean) throws SQLException {
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
	
			//String sql = "select pNo,title,content,to_char(timeposted, 'yyyy.mm.dd') from SEMI_POST";
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT p.pNo,p.title,p.timeposted,p.hits,p.id,m.name FROM( " );
			sql.append(" SELECT row_number() over(order by pNo desc) as rnum, pNo,title,hits, " );
			sql.append(" to_char(timeposted,'YYYY.MM.DD') as timeposted,id " );
			sql.append(" FROM semi_post " );
			sql.append(" ) p,semi_member m where p.id=m.id and rnum between ? and ? " );
			sql.append(" order by pNo desc " );
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagingBean.getStartRowNumber());
			pstmt.setInt(2, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostVO vo = new PostVO();
				vo.setpNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setTimePosted(rs.getString(4));
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	public int getTotalPostCount() throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from semi_post";
			pstmt=con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
}
