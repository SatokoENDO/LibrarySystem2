package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.SQLRuntimeException;

public class ReservationDao {

	// 予約
	public void insert(Connection connection, int loginUserId, int bookId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO reservations ( ");
			sql.append(" book_id ");
			sql.append(" , user_Id");
			sql.append(" , reservation_type ");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", 0");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, bookId);
			ps.setInt(2, loginUserId);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

		PreparedStatement ps1 = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE users SET");
			sql.append(" reserved_books = reserved_books +1 ");
			sql.append(" WHERE id = "+ loginUserId );

			ps1 = connection.prepareStatement(sql.toString());

			ps1.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps1);
		}

		PreparedStatement ps2 = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE books SET");
			sql.append(" reservation_number = reservation_number +1 ");
			sql.append(" WHERE id = "+ bookId );

			ps2 = connection.prepareStatement(sql.toString());

			ps2.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps2);
		}
	}
//	本の取寄せ
	public void request(Connection connection, int loginUserId, int bookId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO reservations ( ");
			sql.append(" book_id ");
			sql.append(" , user_Id");
			sql.append(" , reservation_type ");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(" , 1");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, bookId);
			ps.setInt(2, loginUserId);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

		PreparedStatement ps2 = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE books SET");
			sql.append(" status = 2 ");
			sql.append(" WHERE id = "+ bookId );

			ps2 = connection.prepareStatement(sql.toString());

			ps2.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps2);
		}
	}
}
