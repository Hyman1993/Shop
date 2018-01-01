package shop.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import shop.category.service.CategoryService;
import shop.product.service.ProductService;
import shop.product.vo.Product;
import shop.utils.PageBean;

/**
 * 商品的action对象
 * @author Administrator
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用于接受数据的模型驱动
	private Product product=new Product();
	//注入商品的service
	private ProductService productService;
	//接收分类的cid
    private Integer cid;
	//注入一级分类的service
    private CategoryService categoryService;
    //接收当前页数
    private int page;
    //接收二级分类的id
    private Integer csid;
    
    //根据二级分类的id查询商品
    public String findByCsid(){
    	//根据二级商品查询商品
    	 PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
    	 //将pageBean 存入到值栈里面
    	 ActionContext.getContext().getValueStack().set("pageBean",pageBean);
    	 return "findByCsid";
    }
    
	//根据分类的id查询商品
	public String findByCid(){
		//List<Category> cList=categoryService.findAll();
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);//根据一级分类查询商品，带分页查询
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		System.out.println("跳转到productlist页面");
		return "findByCid";
	}
    
	//根据商品的ID进行查询商品，执行方法
	public String findByPid(){
		//调用service的方法来完成查询
		product=productService.findByPid(product.getPid());
		System.out.print("页面跳转到productlist");
		return "findByPid";
	}
	
	
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}


	public ProductService getProductService() {
		return productService;
	}


	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public Integer getCid() {
		return cid;
	}


	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}
     
}
