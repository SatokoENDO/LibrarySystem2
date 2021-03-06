package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			sql.append(" WHERE id = " + loginUserId);

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
			sql.append(" WHERE id = " + bookId);

			ps2 = connection.prepareStatement(sql.toString());

			ps2.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps2);
		}
	}

	// 本の取寄せ
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
			sql.append(" WHERE id = " + bookId);

			ps2 = connection.prepareStatement(sql.toString());

			ps2.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps2);
		}
	}

	public void delete(Connection connection, int loginUserId, int bookId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from reservations ");
			sql.append(" WHERE book_id = ? ");
			sql.append(" and user_id = ? ");

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
			sql.append(" reserved_books = reserved_books -1 ");
			sql.append(" WHERE id = " + loginUserId);

			ps1 = connection.prepareStatement(sql.toString());
			ps1.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

		PreparedStatement ps2 = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append(" reservation_number = reservation_number -1, ");
			sql.append(" notification_time = NULL ");
			sql.append(" WHERE id = " + bookId);

			ps2 = connection.prepareStatement(sql.toString());
			ps2.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	// book.idから次予約または受取申請しているuserIdを探し出す
	public int select(Connection connection, int bookId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"select user_id from reservations WHERE reservation_number=(SELECT MIN(reservation_number) FROM reservations where book_id = ?  )");
			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();

			int reservationUserId = 0;
			while (rs.next()) {
				int id = rs.getInt("user_id");
				reservationUserId = id;
			}
			return reservationUserId;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void updateNotificationTime(Connection connection, int bookId) {
		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE books SET");
			sql.append(" notification_time = CURRENT_TIMESTAMP ");
			sql.append(" WHERE id = ? ;");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, bookId);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, int bookId) {
		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE books SET");
			sql.append(" status = 2 ");
			sql.append(" WHERE id = ? ;");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, bookId);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<Integer> getReservaionUserList(Connection connection){
		PreparedStatement ps = null;

		try {
			Statement statement = connection.createStatement();
			String mySql = "select user_id from reservations WHERE reservation_type = 1";
			ResultSet rs = statement.executeQuery(mySql);
			List<Integer> userIdList = toUserIdList(rs);
			if(userIdList.isEmpty() == true){
				return null;
			} else {
				return userIdList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Integer> toUserIdList(ResultSet rs) throws SQLException {

		List<Integer> ret = new ArrayList<Integer>();
		try {
			while (rs.next()) {
				int userId = rs.getInt("user_id");

				ret.add(userId);

			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Integer> getReservaionBookList(Connection connection){
		PreparedStatement ps = null;

		try {
			Statement statement = connection.createStatement();
			String mySql = "select book_id from reservations WHERE reservation_type = 1";
			ResultSet rs = statement.executeQuery(mySql);
			List<Integer> bookIdList = toBookIdList(rs);
			if(bookIdList.isEmpty() == true){
				return null;
			} else {
				return bookIdList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Integer> toBookIdList(ResultSet rs) throws SQLException {

		List<Integer> ret = new ArrayList<Integer>();
		try {
			while (rs.next()) {
				int bookId = rs.getInt("book_id");

				ret.add(bookId);

			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
