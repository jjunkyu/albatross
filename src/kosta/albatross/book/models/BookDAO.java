package kosta.albatross.book.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import kosta.albatross.common.models.DataSourceManager;
import kosta.albatross.common.models.PagingBean;
import kosta.albatross.exception.NotDeleteBookException;
import kosta.albatross.rent.models.RentDAO;

public class BookDAO {

	private static BookDAO instance = new BookDAO();
	private DataSource dataSource;

	private BookDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static BookDAO getInstance() {
		return instance;
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
	 * 책 리스트를 뽑는 메서드
	 * 페이징빈을 매게변수로 받아 페이징 처리도 같이 함
	 * @param pagingBean
	 * @return list
	 * @throws SQLException
	 */
	public ArrayList<BookVO> getBookList(PagingBean pagingBean) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		BookVO vo = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT b.bNo, b.title, b.author, b.content, b.publisher,b.isRented,b.imagePath ");
			sql.append(
					"FROM(SELECT row_number() OVER(ORDER BY bNo DESC) AS rnum,bNo,title,author,content,publisher,isRented,imagePath ");
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
				vo.setImagePath(rs.getString(7));
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	/**
	 * 책의 상세 정보를 보여주는 메서드 
	 * @param bNo
	 * @return bvo
	 * @throws SQLException
	 */
	public BookVO getBookDetail(int bNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVO bvo = null;
		String sql;
		try {
			con = dataSource.getConnection();
			sql = "SELECT bNo, title, author, content, publisher, isRented, imagePath FROM semi_book where bNo = ?";
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
				bvo.setImagePath(rs.getString(7));
			}
			return bvo;
		} finally {
			closeAll(rs, pstmt, con);
		}
	}
	
	
	/**
	 * 작가로 책을 검색해주는 메서드
	 * @param author
	 * @return list
	 * @throws SQLException
	 */
	public ArrayList<BookVO> searchByAuthor(String author, PagingBean pagingBean) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		BookVO bookVO = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT b.bNo, b.title, b.author, b.content, b.publisher,b.isRented ");
			sql.append("FROM(SELECT row_number() OVER(ORDER BY bNo DESC) ");
			sql.append("AS rnum,bNo,title,author,content,publisher,isRented ");
			sql.append("FROM semi_book WHERE author LIKE ?) b WHERE rnum BETWEEN ? AND ? ");
			sql.append("ORDER BY bNo DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + author + "%");
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookVO = new BookVO();
				bookVO.setbNo(rs.getInt(1));
				bookVO.setTitle(rs.getString(2));
				bookVO.setAuthor(rs.getString(3));
				bookVO.setContent(rs.getString(4));
				bookVO.setPublisher(rs.getString(5));
				bookVO.setRented(rs.getInt(6) == 0 ? false : true);
				list.add(bookVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	public int searchByAuthorCount(String author) throws SQLException {
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM semi_book WHERE author LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + author + "%");
			rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}

	public ArrayList<BookVO> searchByTitle(String title,PagingBean pagingBean) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		BookVO bookVO = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT b.bNo, b.title, b.author, b.content, b.publisher,b.isRented ");
			sql.append("FROM(SELECT row_number() OVER(ORDER BY bNo DESC) ");
			sql.append("AS rnum,bNo,title,author,content,publisher,isRented ");
			sql.append("FROM semi_book WHERE title LIKE ?) b WHERE rnum BETWEEN ? AND ? ");
			sql.append("ORDER BY bNo DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + title + "%");
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookVO = new BookVO();
				bookVO.setbNo(rs.getInt(1));
				bookVO.setTitle(rs.getString(2));
				bookVO.setAuthor(rs.getString(3));
				bookVO.setContent(rs.getString(4));
				bookVO.setPublisher(rs.getString(5));
				bookVO.setRented(rs.getInt(6) == 0 ? false : true);
				list.add(bookVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	public int searchByTitleCount(String title) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM semi_book WHERE title LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + title + "%");
			rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}

	public ArrayList<BookVO> searchByTitleAndAuthor(String pattern, PagingBean pagingBean) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		BookVO bookVO = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT b.bNo, b.title, b.author, b.content, b.publisher,b.isRented ");
			sql.append("FROM(SELECT row_number() OVER(ORDER BY bNo DESC) ");
			sql.append("AS rnum,bNo,title,author,content,publisher,isRented ");
			sql.append("FROM semi_book WHERE title LIKE ? or author LIKE ?) b WHERE rnum BETWEEN ? AND ? ");
			sql.append("ORDER BY bNo DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + pattern + "%");
			pstmt.setString(2, "%" + pattern + "%");
			pstmt.setInt(3, pagingBean.getStartRowNumber());
			pstmt.setInt(4, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookVO = new BookVO();
				bookVO.setbNo(rs.getInt(1));
				bookVO.setTitle(rs.getString(2));
				bookVO.setAuthor(rs.getString(3));
				bookVO.setContent(rs.getString(4));
				bookVO.setPublisher(rs.getString(5));
				bookVO.setRented(rs.getInt(6) == 0 ? false : true);
				list.add(bookVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	public int searchByTitleAndAuthorCount(String pattern) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM semi_book WHERE title LIKE ? OR author LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + pattern + "%");
			pstmt.setString(2, "%" + pattern + "%");
			rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}

	/**
	 * 책의 총 권수를 찾는 메서드
	 * @return count
	 * @throws SQLException
	 */
	public int getTotalBookCount() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM semi_book";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
	
	/**
	 * 책의 상태변화 즉 대여가능 상태인지 아닌지 확인하는 메서드
	 * @param bNo
	 * @param isRented
	 * @throws SQLException
	 */
	public void changeOfRented(int bNo, String isRented) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			if (isRented.equals("false")) {
				String sql = "UPDATE semi_book SET isRented = 1 WHERE bNo = ?";
				pstmt = con.prepareStatement(sql);
			}else {
				String sql = "UPDATE semi_book SET isRented = 0 WHERE bNo = ?";
				pstmt = con.prepareStatement(sql);
			}
			pstmt.setInt(1, bNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(rs, pstmt, con);
		}
	}
	
	/**
	 * 도서관에 있는 책을 리스트에서 지우는 메서드
	 * 
	 * @param bNo
	 * @throws SQLException
	 * @throws NotDeleteBookException 
	 */
	public void deleteBook(int bNo) throws SQLException, NotDeleteBookException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			if(RentDAO.getInstance().isRentingBook(bNo)) {
				throw new NotDeleteBookException();
			}
			con = dataSource.getConnection();
			String sql = "DELETE FROM SEMI_BOOK WHERE bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.executeQuery();
		} finally {
			closeAll(pstmt, con);
		}
	}
	
	/**
	 *  책 등록 메소드
	 *  
	 * @param vo
	 * @throws SQLException
	 */
	public BookVO bookRegister(BookVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVO BVO = null;
		String sql;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO semi_book(bNo,title,content,author,publisher,imagePath) VALUES(semi_book_seq.nextval,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getAuthor());
			pstmt.setString(4, vo.getPublisher());
			pstmt.setString(5, vo.getImagePath());
			pstmt.executeQuery();
			pstmt.close();
			String sql2 = "SELECT semi_book_seq.currval FROM dual";
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				BVO = getBookDetail(rs.getInt(1));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return BVO;
	}
}
