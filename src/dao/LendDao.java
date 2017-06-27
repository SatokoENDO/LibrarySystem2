package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Book;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class LendDao {

	public void update(Connection connection, Book book) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append(" status = 1 ");
			sql.append(" ,borrower = ? ");
			sql.append(" ,borrowed_time = CURRENT_TIMESTAMP ");
			sql.append(" ,due_date = CURRENT_TIMESTAMP + interval 14 day ");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, book.getBorrower());
			ps.setInt(2, book.getId());

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

		PreparedStatement ps1 = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" borrow_books = borrow_books + 1 ");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps1 = connection.prepareStatement(sql.toString());

			ps1.setInt(1, book.getBorrower());

			int count = ps1.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps1);
		}

		PreparedStatement ps2 = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from reservations ");
			sql.append(" WHERE");
			sql.append(" user_id = ?");
			sql.append(" and book_id = ?");

			ps2 = connection.prepareStatement(sql.toString());

			ps2.setInt(1, book.getBorrower());
			ps2.setInt(2, book.getId());

			int count = ps2.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps2);
		}
	}
}
