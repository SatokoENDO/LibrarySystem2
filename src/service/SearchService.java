package service;

import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Book;
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
}
