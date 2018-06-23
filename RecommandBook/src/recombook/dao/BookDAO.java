package recombook.dao;

import java.util.List;

import recombook.vo.BookVO;


public interface BookDAO extends DAO {
	
	public List<BookVO> inquireBookList(int startRow);

}
