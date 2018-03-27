package kosta.albatross.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

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
}	
