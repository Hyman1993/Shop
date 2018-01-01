package shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import shop.category.categorysecond.service.CategorySecondService;
import shop.category.categorysecond.vo.CategorySecond;
import shop.product.service.ProductService;
import shop.product.vo.Product;
import shop.utils.PageBean;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
    //模型驱动使用的对象
	private Product product =new Product();
//注入商品的service
	private ProductService productService;
	private Integer page;
	//注入二级分类的service
	private CategorySecondService categorySecondService ;
	//文件上传需要的参数
	private File upload;//上传的文件
	 private String uploadFileName;//接收文件上传的文件名
	 private String uploadContextType;//接收文件上传的类型

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	//修改商品的方法
	public String update() throws IOException{
		// 将信息修改到数据库
		product.setPdate(new Date());
		
		// 上传:
		if(upload != null ){
			String delPath = ServletActionContext.getServletContext().getRealPath(
					"/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		// 页面跳转
		return "updateSuccess";
	}

	
	//编辑商品的方法
	public String edit(){
		//根据商品的id查询该商品
		product=productService.findByPid(product.getPid());
		//查询所有的二级分类的集合
		List<CategorySecond> csList=categorySecondService.findAll();
		//将数据保存到页面
		ActionContext.getContext().getValueStack().set("csList",csList);
		//页面跳转
		return "editSuccess";
	}
	
	//删除商品的方法
	public String delete(){
		//先查询再删除
		product=productService.findByPid(product.getPid());
		//删除上传的图片
		String path=product.getImage();
		if(path!=null){
		String realPath=ServletActionContext.getServletContext().getRealPath("/"+path);
		 File file=new File(realPath);
		 file.delete();
		}
		//删除商品
		productService.delete(product);
		//页面的跳转
		return "deleteSuccess";
	}
	
	//保存商品的方法
	public String save() throws IOException{
		// 将提交的数据添加到数据库中.
		product.setPdate(new Date());
		// product.setImage(image);
		if(upload != null){
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
	
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	
	
   public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContextType() {
		return uploadContextType;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	//跳转到添加页面的方法
	public String addPage(){
		//查询所有的二级分类的集合
		List<CategorySecond> csList=categorySecondService.findAll();
		//通过值栈保存
		ActionContext.getContext().getValueStack().set("csList", csList);
		//页面的跳转
		return "addPageSuccess";
		
	}
	
	//带分页的查询商品的执行的方法
	public String findAll(){
		//调用service完成查询
		PageBean<Product>pageBean= productService.findByPage(page);
		//将数据传递到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "findAll";
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

	public CategorySecondService getCategorySecondService() {
		return categorySecondService;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
}
