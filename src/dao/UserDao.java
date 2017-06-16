package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.SQLRuntimeException;

public class UserDao {
	// ユーザーの登録
	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append(" card_number");
			sql.append(", name");
			sql.append(", address");
			sql.append(", tel");
			sql.append(", mail");
			sql.append(", register_date");
			sql.append(", password");
			sql.append(", library_id");
			sql.append(", is_admin");
			sql.append(", borrow_books");
			sql.append(") VALUES (");
			sql.append(" ?"); // card_number
			sql.append(", ?"); // name
			sql.append(", ?"); // address
			sql.append(", ?"); // tel
			sql.append(", ?"); // mail
			sql.append(", CURRENT_TIMESTAMP"); // register_time
			sql.append(", ?"); // password
			sql.append(", ?"); // library_id
			sql.append(", ?"); // is_admin
			sql.append(", 0");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setLong(1, user.getCardNumber());
			ps.setString(2, user.getName());
			ps.setString(3, user.getAddress());
			ps.setString(4, user.getTel());
			ps.setString(5, user.getMail());
			ps.setString(6, user.getPassword());
			ps.setInt(7, user.getLibraryId());
			ps.setInt(8, user.getIsAdmin());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	// 最新に登録したユーザーのidを取得
	public int getUserId(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			String mySql = "select id from users WHERE  id=(SELECT MAX(id) FROM users )";
			ResultSet rs = statement.executeQuery(mySql);

			int userId = 0;
			while (rs.next()) {
				int id = rs.getInt("id");
				userId = id;
			}
			return userId;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	// 利用者番号の登録
	public void insert(Connection connection, int cardNumber) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET card_number =? WHERE id=(SELECT MAX(id) FROM (select * from users ) as users) ");

			ps = connection.prepareStatement(sql.toString());

			ps.setLong(1, cardNumber);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, long cardNumber, String password) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users WHERE card_number = ? AND password = ?");
			ps = connection.prepareStatement(sql.toString());
			ps.setLong(1, cardNumber);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);

			if (userList.isEmpty() == true) {
				return null;
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				long cardNumber= rs.getLong("card_number");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String mail = rs.getString("mail");
				String password = rs.getString("password");
				int libraryId = rs.getInt("library_id");
				int borrowBooks = rs.getInt("borrow_books");

				User user = new User();
				user.setId(id);
				user.setCardNumber(cardNumber);
				user.setName(name);
				user.setAddress(address);
				user.setTel(tel);
				user.setMail(mail);
				user.setPassword(password);
				user.setLibraryId(libraryId);
				user.setBorrowBooks(borrowBooks);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
