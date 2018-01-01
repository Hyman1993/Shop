package shop.admin.user.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import shop.admin.user.service.AdminUserService;
import shop.admin.user.vo.AdminUser;
import shop.category.vo.Category;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	// 模型驱动使用的对象
		private AdminUser adminUser = new AdminUser();
	//注入service
    private AdminUserService adminUserService;
	
    //后台登录的方法
    public String login(){
    	//调用service完成登录
    	AdminUser existAdminUser=adminUserService.login(adminUser);
    	if(existAdminUser==null){
    		//登录失败
    		this.addActionError("亲！您的用户名或者密码错误");
    		return "loginFail";
    	}else{
    	//登录成功
    		ServletActionContext.getRequest().getSession().setAttribute("existAdminUser",existAdminUser);
    	return "loginSuccess";
    	}
    }
    


	public AdminUserService getAdminUserService() {
		return adminUserService;
	}
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	public AdminUser getModel() {
		return adminUser;
	}

}
