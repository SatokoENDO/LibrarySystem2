package service;

import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Book;
import beans.User;
import dao.SearchDao;

public class SearchService {

//	本の名前から本の情報を取得する
	public  List<Book> getBookFromName(String bookName, String author, String publisher, int libraryId, int kind){

		Connection connection = null;
		try{
			connection = getConnection();

			SearchDao bookDao = new SearchDao();
			List<Book> book = bookDao.getBookFromName(connection, bookName, author, publisher, libraryId, kind);

			return book ;
		}finally{

		}
	}
//	氏名、住所から利用者を照会する
	public User getUser(String name, String address){

		Connection connection = null;
		try{
			connection = getConnection();

			SearchDao userDao = new SearchDao();
			User user = userDao.getUser(connection, name, address);

			return user ;
		}finally{

		}
	}
}
