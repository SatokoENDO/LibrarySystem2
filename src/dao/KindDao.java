<<<<<<< HEAD
package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Kind;
import exception.SQLRuntimeException;

public class KindDao {

	public List<Kind> getKindList(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			String mySql = "select * from kinds";
			ResultSet rs = statement.executeQuery(mySql);
			List<Kind> kindList = toKindList(rs);
			if (kindList.isEmpty() == true) {
				return null;
			} else {
				return kindList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	private List<Kind> toKindList(ResultSet rs) throws SQLException {

		List<Kind> ret = new ArrayList<Kind>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Kind kind = new Kind();
				kind.setId(id);
				kind.setName(name);

				ret.add(kind);
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

import beans.Kind;
import exception.SQLRuntimeException;

public class KindDao {

	public List<Kind> getKindList(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			String mySql = "select * from kinds";
			ResultSet rs = statement.executeQuery(mySql);
			List<Kind> kindList = toKindList(rs);
			if (kindList.isEmpty() == true) {
				return null;
			} else {
				return kindList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	private List<Kind> toKindList(ResultSet rs) throws SQLException {

		List<Kind> ret = new ArrayList<Kind>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Kind kind = new Kind();
				kind.setId(id);
				kind.setName(name);

				ret.add(kind);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
>>>>>>> 81f42805c7fb3bedbacd964fa48c9cfcf0e2e4a5
}