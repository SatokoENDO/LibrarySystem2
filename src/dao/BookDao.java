package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Book;
import exception.SQLRuntimeException;

public class BookDao {
	public void insert(Connection connection, Book book) {

		PreparedStatement ps = null;
		try {
			StringBuilder mySql = new StringBuilder();
			mySql.append("insert into books (");
			mySql.append("library_id");
			mySql.append(", shelf_number");
			mySql.append(", isbn");
			mySql.append(", name");
			mySql.append(", author_name");
			mySql.append(", publisher_name");
			mySql.append(", kind");
			mySql.append(") values (");
			mySql.append("?");
			mySql.append(", ?");
			mySql.append(", ?");
			mySql.append(", ?");
			mySql.append(", ?");
			mySql.append(", ?");
			mySql.append(", ?");
			mySql.append(")");

			ps = connection.prepareStatement(mySql.toString());

			ps.setInt(1, book.getLibraryId());
			ps.setInt(2, book.getShelfNumber());
			ps.setString(3, book.getISBN());
			ps.setString(4, book.getName());
			ps.setString(5, book.getAuthorName());
			ps.setString(6, book.getPublisherName());
			ps.setInt(7, book.getKind());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
