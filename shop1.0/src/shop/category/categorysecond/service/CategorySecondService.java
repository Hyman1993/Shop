package shop.category.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import shop.category.categorysecond.dao.CategorySecondDao;
import shop.category.categorysecond.vo.CategorySecond;
import shop.utils.PageBean;

/**
 * 二级分类管理的业务层
 * @author Administrator
 *
 */
@Transactional
public class CategorySecondService {
   //注入二级分类的Dao
	private CategorySecondDao categorySecondDao;

	public CategorySecondDao getCategorySecondDao() {
		return categorySecondDao;
	}

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
//业务层分类查询二级分类的方法
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean=new PageBean<CategorySecond>();
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit=10;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount=categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount % limit ==0){
			totalPage=totalCount / limit;
		}else{
			totalPage=totalCount /limit +1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置页面显示数据的集合:
		int begin = (page - 1) * limit;
		//设置每页显示数据集合
		List<CategorySecond> list=categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);

		return pageBean;
	
	}
//业务层，保存二级分类的方法
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
		
	}
//业务层根据二级分类的id查询二级分类
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
		
	}
//删除二级分类的方法
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
		
	}
//业务层，修改二级分类的方法
	public void update(CategorySecond categorySecond) {
	  categorySecondDao.update(categorySecond);
	}
//业务层，查询所有二级分类的方法
	public List<CategorySecond> findAll() {
	return categorySecondDao.findAll();
		
	}


}
