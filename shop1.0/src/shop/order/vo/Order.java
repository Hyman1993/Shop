package shop.order.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import shop.user.vo.User;

/**
 * 订单模块的Action
 * @author Administrator
 *
 */
public class Order{

	private Integer oid;
	private Date orderTime;
	private Double total;
	private Integer state;
	private String name;
	private String addr;
	private String phone;
	
	//订单所属的用户
	private User user;
	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	//订单中所属的多个订单项
	private Set<OrderItem> orderItems=new HashSet<OrderItem>();
	

   
}
