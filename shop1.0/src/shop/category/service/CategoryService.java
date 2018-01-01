package shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import shop.category.dao.CategoryDao;
import shop.category.vo.Category;

/**
 * 一级分类业务层
 * @author Administrator
 *
 */
@Transactional
public class CategoryService {
   //注入CategoryDao
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}
//业务层保存一级分类的方法
	public void save(Category category) {
		categoryDao.save(category);
	}
//业务根据cid查询一级分类
	public Category findByCid(Integer cid) {
		
		return categoryDao.findByCid(cid);
	}
//业务层删除一级分类的方法
	public void delete(Category category) {
	  categoryDao.delete(category);
		
	}
//业务层，修改一级分类的方法
	public void update(Category category) {
		categoryDao.update(category);
		
	}
	
}
