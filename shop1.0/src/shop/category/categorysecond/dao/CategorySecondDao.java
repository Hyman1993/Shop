package shop.category.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import shop.category.categorysecond.vo.CategorySecond;
import shop.utils.PageHibernateCallback;

/**
 * 二级分类管理的Dao层
 * @author Administrator
 *
 */
public class CategorySecondDao extends HibernateDaoSupport{

	//Dao层的统计二级分类的方法
	public int findCount() {
		String hql="select count(*) from CategorySecond";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list !=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
//dao层分类查询二级分类的方法
	public List<CategorySecond> findByPage(int begin,int limit) {
		String hql="from CategorySecond order by csid desc";
		List<CategorySecond> list=this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql,null,begin,limit));
		 if(list !=null && list.size()>0){
			 return list;
		 }
		return null;
	}
	//Dao层的保存二级分类的方法
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);		
	}
	//DAO层根据二级分类的id查询二级分类
	public CategorySecond findByCsid(Integer csid) {
	     return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}
	//DAO层删除二级分类的方法
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
		
	}
	//DAO层修改二级分类的方法
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
		
	}
	//DAO层查询所有的方法
	public List<CategorySecond> findAll() {
		String hql="from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}

}
