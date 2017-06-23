package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.SQLRuntimeException;

public class ReservationDao {

	//予約
	public void insert(Connection connection, int loginId, int bookId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO reservations ( ");
			sql.append(" book_id ");
			sql.append(" , user_Id");
			sql.append(") VALUES (");
			sql.append(" ?"); // card_number
			sql.append(", ?"); // name

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, bookId);
			ps.setInt(2, loginId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
