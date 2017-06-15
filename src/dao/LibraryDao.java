package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Library;
import exception.SQLRuntimeException;

public class LibraryDao {
	public List<Library> getLibraries(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM library.libraries ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Library> ret = toLibrariesList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Library> toLibrariesList(ResultSet rs)
			throws SQLException {

		List<Library> ret = new ArrayList<Library>();
		try{
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Library libraryList = new Library();
				libraryList.setId(id);
				libraryList.setName(name);

				ret.add(libraryList);
			}
			return ret;
		}finally{
			close(rs);
		}
	}
}
