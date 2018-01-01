package shop.admin.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import shop.admin.user.vo.AdminUser;

/**
 * 后台登录的Dao类
 * @author Administrator
 *
 */
public class AdminUserDao extends HibernateDaoSupport{
   /**
    * dao中登录的方法
    * @param adminUser
    * @return
    */
	public AdminUser login(AdminUser adminUser) {
		String hql="from AdminUser where username=? and password=?";
		List<AdminUser> list=this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
    
}
