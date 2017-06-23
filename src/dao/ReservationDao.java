package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.SQLRuntimeException;

public class ReservationDao {

	//予約
	public void insert(Connection connection, int loginUserId, int bookId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO reservations ( ");
			sql.append(" book_id ");
			sql.append(" , user_Id");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
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
	}
}
