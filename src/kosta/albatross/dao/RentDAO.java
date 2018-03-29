package kosta.albatross.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class RentDAO {
	private static RentDAO instance = new RentDAO();
	private Connection con;
	private String sql;
	private ResultSet rs;
	private DataSource ds;
	private PreparedStatement pstmt;
	
	private RentDAO() {
		ds = DataSourceManager.getInstance().getDataSource();
	}
	
	public static RentDAO getInstance() {
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
	
	public void addRentItem(String id,int bNo) throws SQLException {
		try {
			con = ds.getConnection();
			sql = "insert into SEMI_RENT_BOOK(id,bNo,rentdate,returndate) values(?,?,sysdate,sysdate+7)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setInt(2, bNo);
			pstmt.executeQuery();
		} finally {
			closeAll();
		}
	}
	
	public void deleteRentItem(int bNo) throws SQLException {
		try {
			con = (Connection) DataSourceManager.getInstance().getDataSource();
			sql = "delete from SEMI_RENT_BOOK where bNo = 1;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.executeQuery();
		} finally {
			closeAll();
		}

	}
	
	public boolean isRentLate(int bNo) throws SQLException {
		int remainDate = 0;
		try {
			con = (Connection) DataSourceManager.getInstance().getDataSource();
			sql = "select returndate-sysdate from SEMI_RENT_BOOK where bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			rs = pstmt.executeQuery();
			if(rs.next())
				remainDate = rs.getInt(1);
			if(remainDate<0)
				return true;
			else
				return false;
		} finally {
			closeAll();
		}
	}
}
