package shop.user.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.transaction.annotation.Transactional;

import shop.user.dao.UserDao;
import shop.user.vo.User;
import shop.utils.MailUtils;
import shop.utils.PageBean;
import shop.utils.UUIDUtils;

/*
 * 用户模块业务层的代码
 */
@Transactional
public class UserService {
    //注入UserDao
	private UserDao userDao;

	//按用户名查询用户的方法
	public User findByUsername(String username){
		return userDao.findByName(username);
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void save(User user) throws AddressException, MessagingException {
	//业务层完成用户注册
		user.setState(0);//0代表用户未激活，1代表用户激活
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		//调用dao完成插入保存操作
		userDao.save(user);
		//发送激活邮件
		MailUtils.sendMail(user.getEmail(),code);
	}

	//根据激活码查询用户
	public User findByCode(String code) {
		
		return userDao.findByCode(code);
	}
 //修改用户状态的方法
	public void  update(User existUser) {
		userDao.update(existUser);
		
	}
//用户登录的方法
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
	//业务层查询管理用户
	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}
//业务层分页查询所有管理员
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		int begin = (page - 1)*limit;
		List<User> list = userDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
//业务层删除管理账户
	public void delete(User existUser) {
		userDao.delete(existUser);
	}
}
