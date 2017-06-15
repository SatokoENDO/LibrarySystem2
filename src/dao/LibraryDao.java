package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Library;
import exception.SQLRuntimeException;

public class LibraryDao {

	public List<Library> getLibraryList(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			String mySql = "select * from libraries";
			ResultSet rs = statement.executeQuery(mySql);
			List<Library> libraryList = toLibraryList(rs);
			if (libraryList.isEmpty() == true) {
				return null;
			} else {
				return libraryList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	private List<Library> toLibraryList(ResultSet rs) throws SQLException {

		List<Library> ret = new ArrayList<Library>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Library library = new Library();
				library.setId(id);
				library.setName(name);

				ret.add(library);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}