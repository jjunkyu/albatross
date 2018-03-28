package kosta.albatross.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import kosta.albatross.vo.BookVO;

public class BookDAO {
	
	private static BookDAO instance = new BookDAO();
	private Connection con;
	private String sql;
	private ResultSet rs;
	private DataSource ds;
	private PreparedStatement pstmt;
	
	private BookDAO() {
		ds = DataSourceManager.getInstance().getDataSource();
	}
	
	public static BookDAO getInstance() {
		return instance;
	}
	
	private void closeAll() {
		try {
			if(con != null) con.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BookVO> getBookList() throws SQLException{
		ArrayList<BookVO> list= new ArrayList<BookVO>();
		try {
		con = (Connection) DataSourceManager.getInstance().getDataSource();
		sql="SELECT bNo,title,content,author,publisher FROM semi_book";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			list.add(new BookVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
		}
		}finally {
			closeAll();
		}
		return list;
	}
}	
