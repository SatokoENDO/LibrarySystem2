package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Book;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class BookDao {
	public void insert(Connection connection, Book book) {

		PreparedStatement ps = null;
		try {
			StringBuilder mySql = new StringBuilder();
			mySql.append("insert into books (");
			mySql.append("library_id");
			mySql.append(", shelf_id");
			mySql.append(", isbn");
			mySql.append(", name");
			mySql.append(", author");
			mySql.append(", publisher");
			mySql.append(", kind");
			mySql.append(", status");
			mySql.append(", borrower");
			mySql.append(", borrowed_time");
			mySql.append(", returned_time");
			mySql.append(", reservation_number");
			//status, borrower, borrowed_time, returned_time, reservation_numberもデフォルト値を設定する
			mySql.append(") values (");
			mySql.append("?"); //library_id
			mySql.append(", ?"); //shelf_number
			mySql.append(", ?"); //isbn
			mySql.append(", ?"); //name
			mySql.append(", ?"); //author_name
			mySql.append(", ?"); //publisher_name
			mySql.append(", ?"); //kind
			mySql.append(", 0"); //status
			mySql.append(", 0"); //borrower
			mySql.append(", 0000-00-00"); //borrowed_time
			mySql.append(", 0000-00-00"); //returned_time
			mySql.append(", 0"); //reservation_number
			mySql.append(")");

			ps = connection.prepareStatement(mySql.toString());

			ps.setInt(1, book.getLibraryId());
			ps.setInt(2, book.getShelfId());
			ps.setString(3, book.getISBN());
			ps.setString(4, book.getName());
			ps.setString(5, book.getAuthor());
			ps.setString(6, book.getPublisher());
			ps.setInt(7, book.getKind());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public Book getBook(Connection connection, int id){

		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM books WHERE ID = ? ";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<Book> bookList = toBookList(rs);

			System.out.println(bookList);

			if(bookList.isEmpty() == true) {
				return null;
			}else{
				return bookList.get(0);
			}
		}catch (SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	private List<Book> toBookList(ResultSet rs) throws SQLException {

		List<Book> ret = new ArrayList<Book>();
		try {
			while (rs.next()) {
				int libraryId = rs.getInt("library_id");
				String bookName = rs.getString("name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");

				Book book = new Book();

				book.setLibraryId(libraryId);
				book.setName(bookName);
				book.setAuthor(author);
				book.setPublisher(publisher);

				ret.add(book);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	//最新のid（一連番号）を取得
	public int getBookId(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			String mySql = "select id from books WHERE  id=(SELECT MAX(id) FROM books )";
			ResultSet rs = statement.executeQuery(mySql);

			int bookId = 0;
			while (rs.next()) {
				int id = rs.getInt("id");
				bookId = id;
			}
			return bookId;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	public void returnBook(Connection connection, int bookId) {

		PreparedStatement ps = null;
		try {
			StringBuilder mySql = new StringBuilder();
			mySql.append("update books set");
			mySql.append(" status = 0");
			mySql.append(",borrowed_time = 0000-00-00");
			mySql.append(", returned_time =CURRENT_TIMESTAMP ");
			mySql.append(" where");
			mySql.append(" id = ?");
			mySql.append(";");

			ps = connection.prepareStatement(mySql.toString());

			ps.setInt(1, bookId);

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
