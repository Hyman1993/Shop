package shop.order.adminorder;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import shop.order.service.OrderService;
import shop.order.vo.Order;
import shop.order.vo.OrderItem;
import shop.utils.PageBean;

/**
 * 后台订单管理的action
 * @author Administrator
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
   private Order order=new Order();
   private OrderService orderService;
   private Integer page;
	@Override
	public Order getModel() {
		return order;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//根据订单id查询订单项
	public String findOrderItem(){
		System.out.println("执行action");
		//根据订单id查询订单项
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
	  //通过值栈显示到页面上:
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	//在分页的查询的执行的方法
	public String findAll(){
		//分页查询
		PageBean<Order> pageBean=orderService.findByPage(page);
		//通过值栈保存数据到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面的跳转
		return "findAll";
	}
	//修改订单状态的方法：
	public String updateState(){
		//1.根据订单id查询订单
		System.out.println("oid:"+order.getOid());
		Order currOrder=orderService.findByOid(order.getOid());
		//2.修改订单状态
		currOrder.setState(3);
		orderService.update(currOrder);
		//3.页面跳转
		return "updateSuccess";
	}
       
}
