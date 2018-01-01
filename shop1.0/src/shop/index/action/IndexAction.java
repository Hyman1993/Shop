package shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import shop.category.service.CategoryService;
import shop.category.vo.Category;
import shop.product.service.ProductService;
import shop.product.vo.Product;

public class IndexAction extends ActionSupport{
   
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    //注入一级分类的service
	private CategoryService categoryService;
	//注入商品的serice
	private ProductService productService;
	
	//访问主页 
	public String execute(){
		System.out.println("去首页...");
		//查询所有一级分类
		List<Category> cList=categoryService.findAll();
		 //将一级分类存入到session的范围
		ActionContext.getContext().getSession().put("cList",cList);
		
		//查询热门商品
		List<Product> hList=productService.findHot();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//查询最新的商品
		List<Product> nList=productService.findNew();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("nList",nList);
		return "index";
	 }
	public CategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}
