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

	//返却：棚に戻す
	public void returnBookToShelf(Book bookInfo){
		Connection connection = null;
		try {
			connection = getConnection();

			BookDao bookDao = new BookDao();
			bookDao.returnBookToShelf(connection, bookInfo);

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

	//返却：整理中にする
	public void returnBookToFront(Book book){
		Connection connection = null;
		try {
			connection = getConnection();

			BookDao bookDao = new BookDao();
			bookDao.returnBookToFront(connection, book);

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

//  最後に追加された本のidをゲットする
	public int getBookId() {

		Connection connection = null;
		try {
			connection = getConnection();

			BookDao idDao = new BookDao();
			int ret = idDao.getBookId(connection);

			commit(connection);

			return ret;
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

}