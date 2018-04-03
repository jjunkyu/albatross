package kosta.albatross.book.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import kosta.albatross.common.models.DataSourceManager;
import kosta.albatross.common.models.PagingBean;

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
			if (con != null)
				con.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BookVO> getBookList(PagingBean pagingBean) throws SQLException {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		BookVO vo = null;
		try {
			con = ds.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT b.bNo, b.title, b.author, b.content, b.publisher,b.isRented ");
			sql.append(
					"FROM(SELECT row_number() OVER(ORDER BY bNo DESC) AS rnum,bNo,title,author,content,publisher,isRented ");
			sql.append("FROM semi_book) b WHERE rnum BETWEEN ? AND ? ");
			sql.append("ORDER BY bNo DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagingBean.getStartRowNumber());
			pstmt.setInt(2, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BookVO();
				int rented = 0;
				vo.setbNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setAuthor(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setPublisher(rs.getString(5));
				rented = rs.getInt(6);
				if (rented == 0)
					vo.setRented(false);
				else
					vo.setRented(true);
				list.add(vo);
			}
		} finally {
			closeAll();
		}
		return list;
	}

	public BookVO getBookDetail(int bNo) throws SQLException {
		BookVO bvo = null;
		try {
			con = ds.getConnection();
			sql = "SELECT bNo, title, author, content, publisher, isRented FROM semi_book where bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int rented = 0;
				bvo = new BookVO();
				bvo.setbNo(rs.getInt(1));
				bvo.setTitle(rs.getString(2));
				bvo.setAuthor(rs.getString(3));
				bvo.setContent(rs.getString(4));
				bvo.setPublisher(rs.getString(5));
				rented = rs.getInt(6);
				if (rented == 0)
					bvo.setRented(false);
				else
					bvo.setRented(true);
			}
			return bvo;
		} finally {
			closeAll();
		}
	}

	public ArrayList<BookVO> searchByAuthor(String author) throws SQLException {
		ArrayList<BookVO> list = null;

		try {
			con = ds.getConnection();
			sql = "SELECT bNo, title, author, content, publisher, isRented FROM semi_book where author like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + author + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (list == null) {
					list = new ArrayList<>();
				}
				list.add(new BookVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6) == 0 ? false : true));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return list;
	}

	public ArrayList<BookVO> searchByTitle(String title) throws SQLException {
		ArrayList<BookVO> list = null;

		try {
			con = ds.getConnection();
			sql = "SELECT bNo, title, author, content, publisher, isRented FROM semi_book where title like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + title + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (list == null) {
					list = new ArrayList<>();
				}
				list.add(new BookVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6) == 0 ? false : true));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return list;
	}

	public ArrayList<BookVO> searchByTitleAndAuthor(String pattern) throws SQLException {
		ArrayList<BookVO> list = null;
		StringBuilder sql = new StringBuilder();
		try {
			con = ds.getConnection();
			sql.append(" SELECT bNo, title, author, content, publisher, isRented ");
			sql.append(" FROM semi_book ");
			sql.append(" WHERE title LIKE ? ");
			sql.append(" OR author LIKE ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + pattern + "%");
			pstmt.setString(2, "%" + pattern + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (list == null) {
					list = new ArrayList<>();
				}
				list.add(new BookVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6) == 0 ? false : true));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return list;
	}

	public int getTotalBookCount() throws SQLException {
		int count = 0;
		try {
			con = ds.getConnection();
			String sql = "SELECT count(*) FROM semi_book";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll();
		}
		return count;
	}

	public void changeOfRented(int bNo, String isRented) throws SQLException {
		try {
			con = ds.getConnection();
			if (isRented.equals("false"))
				sql = "update semi_book set isRented = 1 where bNo = ?";
			else
				sql = "update semi_book set isRented = 0 where bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.executeUpdate();
		} finally {
			closeAll();
		}
	}

	public void deleteBook(int bNo) throws SQLException {
		try {
			con = ds.getConnection();
			sql = "delete from semi_book where bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.executeUpdate();
		} finally {
			closeAll();
		}
	}
	/**
	 * 
	 * @param vo
	 * @throws SQLException
	 */
	public void bookRegister(BookVO vo) throws SQLException {
		try {
			con = ds.getConnection();
			sql = "SELECT INTO semi_book(bNo,title,content,author,publisher) VALUES(semi_book_seq.nextval,?,?,?,?)";
			pstmt.setInt(1, vo.getbNo());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getAuthor());
			pstmt.setString(5, vo.getPublisher());
			pstmt.executeUpdate();
		} finally {
			closeAll();
		}
	}
}
