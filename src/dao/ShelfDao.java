<<<<<<< HEAD
package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Shelf;
import exception.SQLRuntimeException;

public class ShelfDao {

	public List<Shelf> getShelfList(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			String mySql = "select * from shelves";
			ResultSet rs = statement.executeQuery(mySql);
			List<Shelf> shelfList = toShelfList(rs);
			if (shelfList.isEmpty() == true) {
				return null;
			} else {
				return shelfList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	private List<Shelf> toShelfList(ResultSet rs) throws SQLException {

		List<Shelf> ret = new ArrayList<Shelf>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Shelf shelf = new Shelf();
				shelf.setId(id);
				shelf.setName(name);

				ret.add(shelf);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
=======
package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Shelf;
import exception.SQLRuntimeException;

public class ShelfDao {

	public List<Shelf> getShelfList(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			String mySql = "select * from shelves";
			ResultSet rs = statement.executeQuery(mySql);
			List<Shelf> shelfList = toShelfList(rs);
			if (shelfList.isEmpty() == true) {
				return null;
			} else {
				return shelfList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	private List<Shelf> toShelfList(ResultSet rs) throws SQLException {

		List<Shelf> ret = new ArrayList<Shelf>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Shelf shelf = new Shelf();
				shelf.setId(id);
				shelf.setName(name);

				ret.add(shelf);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
>>>>>>> 81f42805c7fb3bedbacd964fa48c9cfcf0e2e4a5
}