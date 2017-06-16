package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.Book;
import dao.BookDao;

public class BookService {

	public void register(Book book) {

		Connection connection = null;
		try {
			connection = getConnection();

			BookDao bookDao = new BookDao();
			bookDao.insert(connection, book);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
//	本のidから本の情報を取得する
	public Book getBook(int id){

		Connection connection = null;
		try{
			connection = getConnection();

			BookDao bookDao = new BookDao();
			Book book = bookDao.getBook(connection, id);
			return book ;
		}finally{

		}

	}
}
