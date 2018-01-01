package shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import shop.user.vo.User;
import shop.utils.PageHibernateCallback;

/*
 * 业务模块持久层的代码
 */
public class UserDao extends HibernateDaoSupport {

	//DAO层，分页查询
	public List<User> findByPage(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<User>(hql, null, begin, limit));
		return list;
	}
	

	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}
//DAO层，删除用户
	public void delete(User existUser) {
		this.getHibernateTemplate().delete(existUser);
	}
	
	//DAO层，查询管理员数
	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
		public User findByName(String username){
		  //按名称来查询是否有该用户
		String hql="from User where username=?";
		  List<User> list=this.getHibernateTemplate().find(hql,username);
	     if(list!=null && list.size()>0){
	    	  System.out.println("连接到数据库");
	    	 return list.get(0);
	     }
	     System.out.println("未连接到数据库");
	     return null;
	}

	/**
	 * 注册用户存入数据库代码实现
	 * @param user
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	public User findByCode(String code) {
		String hql="from User where code=?";
		List<User> list=this.getHibernateTemplate().find(hql,code);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public  void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	public User login(User user) {
		String hql="from User where username=? and password=?";
		List<User> list=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword());
    if(list!=null&&list.size()>0){
    	return list.get(0);
    }else
		return null;
	}
}
