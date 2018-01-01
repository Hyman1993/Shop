package shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import shop.order.vo.Order;
import shop.order.vo.OrderItem;
import shop.utils.PageHibernateCallback;

/**
 * 订单模块的持久层
 * @author Administrator
 *
 */
public class OrderDao extends HibernateDaoSupport{

	public void save(Order order) {
        this.getHibernateTemplate().save(order);		
	}
  //dao层的我的订单的个数统计
	public Integer findByCountUid(Integer uid) {
		String hql="select count(*) from Order o  where o.user.uid=?";
		List<Long> list=this.getHibernateTemplate().find(hql,uid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}
  //dao层的我的订单的查询
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
	String hql="from Order o where o.user.uid=? order by ordertime desc";
	List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,new Object[]{uid},begin,limit));
		return list;
	}
	public Order findByOid(Integer oid) {
		
		return this.getHibernateTemplate().get(Order.class,oid);
	}
	//dao层修改订单的操作
	public void update(Order currOrder) {
		
		 this.getHibernateTemplate().update(currOrder);;
		 
	}
	//DAO层统计订单的个数
	public int findByCount() {
		String hql="select count(*) from Order";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//DAO层带分页查询的方法
	public List<Order> findByPage(int begin, int limit) {
	String hql="from Order order by ordertime desc";
	List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,null,begin,limit));
	  if(list!=null && list.size()>0){
		  return list;
	  }
		return null;
	}
	//DAO层根据订单id查询订单项的方法
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list=this.getHibernateTemplate().find(hql,oid);
		 if(list!=null && list.size()>0){
			  return list;
		  }
		return null;
	}
    
}
