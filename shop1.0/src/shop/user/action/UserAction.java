package shop.user.action;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import shop.user.service.UserService;
import shop.user.vo.User;

public class UserAction extends ActionSupport implements ModelDriven<User>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 //模型驱动使用的对象
	private User user=new User();
 private UserService userService;
 
 //接受验证码
 private String checkcode;
 
 
 /**
  * 退出的方法
  */
 public String quit(){
	 ServletActionContext.getRequest().getSession().invalidate();
   return "quit";
 }
 
 /**
  * 登录的方法
  */
 public String login(){
	 User existUser=userService.login(user);
	 if(existUser==null){
		 //登录失败
		 this.addActionError("登录：用户名或密码错误或未激活!");
		 return LOGIN;
	 }else{
		 //登录成功
		 //将用户的信息存入到session中
		 ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
	    //页面跳转
		 return "loginSuccess";
	 }
	
 }
 
 /**
  * 调转到登录页面
  */
 public String loginPage(){
	 System.out.println("页面跳转");
	 return "loginPage";
 }
 
	/**
     * 跳转到注册页面的执行方法
     */
	public String registPage(){
		 return "registPage";
	}
	
	/**
	 * Ajax进行异步校验用户名的执行方法
	 * @return
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		/*
		 * 调用service进行查询
		 */
		User existUser=userService.findByUsername(user.getUsername());
		//获得response对象，向页面输出
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		
		if(existUser!=null){
			//查询到该用户：用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}else{
			//没查询到该用户：用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	/**
	 * 用户注册的方法
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public String regist() throws AddressException, MessagingException{
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("验证码输入错误!");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功！请去邮箱激活");
		return "msg";
	}

	
	/**
	 * 用户激活的方法
	 */
	public String active(){
		//根据激活码查询用户
		User existUser=userService.findByCode(user.getCode());
		if(existUser==null){
			//激活码错误
			this.addActionMessage("激活失败：激活码错误");
		}else{
			//激活成功
			//修改用户的状态
			existUser.setState(1);
			existUser.setCode(null);
			
			userService.update(existUser);
			this.addActionMessage("激活成功：请去登录");
		}
		
		return "msg";
	}
	
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	
}
