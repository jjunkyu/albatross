package kosta.albatross.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import kosta.albatross.vo.BookVO;

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
	
	public ArrayList<BookVO> rentList(String id) throws SQLException {
		try {
			ArrayList<BookVO> rentList = new ArrayList<BookVO>();
			BookVO bookVO = null;
			con = ds.getConnection();
			sql = "SELECT b.bNo,b.title,b.author,b.publisher\r\n" + 
					"FROM SEMI_BOOK b, SEMI_RENT_BOOK rb\r\n" + 
					"WHERE rb.id = ? and b.bNo = rb.bNo";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bookVO = new BookVO();
				bookVO.setbNo(rs.getInt(1));
				bookVO.setTitle(rs.getString(2));
				bookVO.setAuthor(rs.getString(3));
				bookVO.setPublisher(rs.getString(4));
				rentList.add(bookVO);
			}
			return rentList;
		} finally {
			closeAll();
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
