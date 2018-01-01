package shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import shop.category.vo.Category;

/**
 * 一级分类持久层
 * @author Administrator
 *
 */
public class CategoryDao extends HibernateDaoSupport{

	public List<Category> findAll() {
		String hql="from Category";
		List<Category> list=this.getHibernateTemplate().find(hql);
		
		return list;
	}
//DAO层保存一级分类的方法
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
		
	}
	//DAO层根据cid查询一级分类的方法
	public Category findByCid(Integer cid) {	
		return this.getHibernateTemplate().get(Category.class, cid);		
	}
	//后台删除一级分类的方法
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
		
	}
	//Dao层的修改一级分类的方法
	public void update(Category category) {
       this.getHibernateTemplate().update(category);
       
	}

}
