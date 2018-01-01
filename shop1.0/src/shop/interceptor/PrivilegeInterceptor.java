package shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import shop.admin.user.vo.AdminUser;

/**
 * 后台权限校验的拦截器
 * @author Administrator
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	//执行拦截的方法
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//判断session中是否存了后台用户的信息：
		AdminUser existAdminUser=(AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
	 if(existAdminUser==null){
		 //没有登录进行访问
		ActionSupport actionSupport= (ActionSupport) actionInvocation.getAction();
		actionSupport.addActionError("请登录后访问！"); 
		return "loginFail";
	 }
	 //已经登录
		return actionInvocation.invoke();
	}

}
