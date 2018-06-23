package recombook.dao;

import java.util.List;

import recombook.vo.CategoryVO;

public interface CategoryDAO extends DAO{

	public List<CategoryVO> inquireParentCategoryList();
	
	public List<CategoryVO> inquireChildrenCategoryList();
}
