package shop.cart.vo;

import shop.product.vo.Product;

/**
 * 购物项对象
 * @author Administrator
 *
 */
public class CartItem {
	
   private Product product;////购物项中商品的信息
   private int count;
   private double subtotal;//购买某种商品的小计,自动计算
   
   
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public double getSubtotal() {
	return count*product.getShop_price();
}

}
