package shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 * @author Administrator
 *
 */
public class Cart implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//购物项的集合:map的key就是商品的pid,value就是购物项
	private Map<Integer,CartItem> map=new LinkedHashMap<Integer,CartItem>();
       
	//购物中级
	private double total;
	
	//购物车的功能
	//1.将购物项添加到购物车
	 public void addCart(CartItem cartItem){
		 //判断购物车中是否已经存在购物项
		 //如果存在：数量增加   总计=总计+购物项小计
		 //如果不存在：向map中添加购物项  总计=总计+购物项小计
		 //获得商品的id
		 Integer pid=cartItem.getProduct().getPid();
		 //判断购物车中是否已经存在购物项
		 if(map.containsKey(pid)){
			 //存在
			CartItem _cartItem=map.get(pid); //获得购物车中原来的购物项
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		 }else{
			 //不存在
			 map.put(pid, cartItem);
		 }
		 //设置总计的值
		 setTotal(getTotal() + cartItem.getSubtotal());
	 }
	//2.从购物车移除购物项
	public void removeCart(Integer pid){
		//将某个购物项移除购物车
		//总计=总计-移除购物项的小计
	CartItem cartItem= map.remove(pid);
	 setTotal(getTotal() - cartItem.getSubtotal());
	}
	//3.清空购物车
	public void clearCart(){
		//将所有的购物项清空
		map.clear();
		//将总计设置为0
		setTotal(0);
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	//cart对象中有一个叫cartItems属性
	public Collection<CartItem> getCartItems(){
		return map.values();//将map中的value值转成单例的list集合
	}
}
