package shop.admin.user.service;

import org.springframework.transaction.annotation.Transactional;

import shop.admin.user.dao.AdminUserDao;
import shop.admin.user.vo.AdminUser;

/**
 * 后台登录的业务层类
 * @author Administrator
 *
 */
@Transactional
public class AdminUserService {
   //注入Dao
	private AdminUserDao adminUserDao;

	public AdminUserDao getAdminUserDao() {
		return adminUserDao;
	}

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
  /**
   * 业务层登录的方法
   * @param adminUser
   * @return
   */
	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
	}

}
