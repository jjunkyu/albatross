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
	//싱글톤
private static PostDAO dao = new PostDAO();
private PostDAO() {
	dataSource=DataSourceManager.getInstance().getDataSource();
}
public static PostDAO getInstance() {
	return dao;
}
public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) {
	if (rs!=null)
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	if(pstmt!=null)
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	if(con!=null)
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

public void closeAll(PreparedStatement pstmt, Connection con) {
	
	if(pstmt!=null)
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	if(con!=null)
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
	
public ArrayList<PostVO> getPostList() throws SQLException{
	ArrayList<PostVO> list = new ArrayList<PostVO>();
	Connection con=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		con=dataSource.getConnection();
		System.out.println("쿼리전");
		String sql="select pNo,title,content,timeposted from SEMI_POST";
		System.out.println("쿼리후");
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		while(rs.next()) {
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

}
