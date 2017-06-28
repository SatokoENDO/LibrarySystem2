package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

	// 最後に登録したユーザーのidを取得
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

	public User getUser(Connection connection, long cardNumber) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users WHERE card_number = ? ");
			ps = connection.prepareStatement(sql.toString());
			ps.setLong(1, cardNumber);
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

	public User getUser(Connection connection, int userId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users WHERE id = ? ");
			ps = connection.prepareStatement(sql.toString());
			ps.setLong(1, userId);
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

	public List<User> getAllUser(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ");
			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();

			List<User> userName = toUserList(rs);

			if(userName.isEmpty() == true) {
				return null;
			}else{
				return userName;
			}
		}catch (SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
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
				if(!(rs.getString("Mail")).isEmpty()){
					String mail = rs.getString("mail");;
					}
				String mail = rs.getString("mail");
				String password = rs.getString("password");
				int libraryId = rs.getInt("library_id");
				int borrowBooks = rs.getInt("borrow_books");
				int isAdmin = rs.getInt("is_Admin");
				int reservedBooks = rs.getInt("reserved_books");
				Timestamp demandTime = rs.getTimestamp("demand_time");

				User user = new User();

				user.setId(id);
				user.setCardNumber(cardNumber);
				user.setName(name);
				user.setAddress(address);
				user.setTel(tel);
				if((rs.getString("mail")).isEmpty() ==true){
					user.setMail(null);
				} else{
					user.setMail(mail);
				}
				user.setPassword(password);
				user.setLibraryId(libraryId);
				user.setBorrowBooks(borrowBooks);
				user.setIsAdmin(isAdmin);
				user.setReservedBooks(reservedBooks);
				user.setDemandTime(demandTime);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	//借りてる冊数を参照
	public int getBorrowBooks(Connection connection, long cardNumberForBooks) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT borrow_books FROM users WHERE card_number = ? " );
			System.out.println(connection);
			ps = connection.prepareStatement(sql.toString());
			ps.setLong(1, cardNumberForBooks);
			ResultSet rs = ps.executeQuery();

			int borrowBooksCount = 0;
			while (rs.next()) {
				int borrowBooks = rs.getInt("borrow_books");
				borrowBooksCount = borrowBooks;
			}
			return borrowBooksCount;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	//ユーザー編集
	public void update(Connection connection, User user){
		PreparedStatement ps = null;

		try{
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE users SET");
			sql.append(" name = ?");
			sql.append(", address = ?");
			sql.append(", tel = ?");
			if(!(user.getMail()).isEmpty()){
			sql.append(", mail = ?");
			}
			if(!(user.getPassword()).isEmpty()){
			sql.append(", password = ?");
			}
			sql.append(", library_id = ?");
			sql.append(" WHERE card_number = ?");
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getName());
			ps.setString(2, user.getAddress());
			ps.setString(3, user.getTel());
			if((user.getMail()).isEmpty() ==true){
				if((user.getPassword()).isEmpty() ==true){
					ps.setInt(4, user.getLibraryId());
					ps.setLong(5, user.getCardNumber());
				}else{
					ps.setString(4, user.getPassword());
					ps.setInt(5, user.getLibraryId());
					ps.setLong(6, user.getCardNumber());
				}
			}else{
				if((user.getPassword()).isEmpty() ==true){
					ps.setString(4, user.getMail());
					ps.setInt(5, user.getLibraryId());
					ps.setLong(6, user.getCardNumber());
				}else{
					ps.setString(4, user.getMail());
					ps.setString(5, user.getPassword());
					ps.setInt(6, user.getLibraryId());
					ps.setLong(7, user.getCardNumber());
				}
<<<<<<< HEAD
			ps.setString(4, user.getMail());
			ps.setInt(5, user.getLibraryId());
			ps.setLong(6, user.getCardNumber());
=======
>>>>>>> 9e05c0472d76390a3f3608df1fe78e78c1afac05
			}
			ps.executeUpdate();
		}  catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally{
			close(ps);
		}
	}
}

