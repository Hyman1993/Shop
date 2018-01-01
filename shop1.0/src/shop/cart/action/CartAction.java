package shop.cart.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import shop.cart.vo.Cart;
import shop.cart.vo.CartItem;
import shop.product.service.ProductService;
import shop.product.vo.Product;

public class CartAction extends ActionSupport{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//接收pid
	private Integer pid;
	//接收count
	private Integer count;
	private ProductService productService;
	
	//我的购物车
	public String myCart(){
		return "myCart";
	}
	
	//移除购物项的方法
	public String removeCart(){
		//获得购物车的对象
	Cart cart=getCart();
		//调用购物车中移除的方法
		cart.removeCart(pid);
		return "removeCart";
	}
	
	//清空购物车执行的方法
	public String clearCart(){
		//获得购物车的对象
		Cart cart=getCart();
		//调用购物车中清空方法
		cart.clearCart();
		return "clearCart";
	}
	
	//将购物项添加到购物车
		public String addCart(){
		//封装一个cartitem的对象
			CartItem cartItem=new CartItem();
			//设置数量，商品
			cartItem.setCount(count);
			//根据商品id，查询商品
			Product product=productService.findByPid(pid);
			cartItem.setProduct(product);
			//将购物项添加到购物车
			//购物车应该存在session中
			Cart cart=getCart();
			
			cart.addCart(cartItem);
			return "addCart";
		}

		//获得购物车的方法--
	private Cart getCart() {
		  Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
			if(cart==null){
				cart=new Cart();
				ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
			}
		  return cart;
	}
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	
}
