package shop.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import shop.category.service.CategoryService;
import shop.category.vo.Category;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
     private Category category=new Category();
	  //注入一级分类的service
       private CategoryService  categoryService;
	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	//后台修改一级分类的方法
	public  String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
	
	//后台编辑一级分类的方法
	public String edit(){
		//根据一级分类的管理查询一级分类
		category=categoryService.findByCid(category.getCid());
		//页面跳转
		return "editSuccess";
	}
	
	//后台删除一级分类的方法;
	public String delete(){
		//接收cid，可以使用模型驱动，删除一级分类，同时删除二级分类，必须先根据id查询，再进行删除
		category=categoryService.findByCid(category.getCid());
		//删除：
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
    //后台保存一级分类的方法
    public String save(){
    	//调用service进行保存
    	categoryService.save(category);
    	return "saveSuccess";
    	
    }
    
	
	//后台执行查询所有一级分类的方法
	public String findAll(){
		//查询所有一级分类
		List<Category> cList=categoryService.findAll();
		//将集合的数据显示到页面
		ActionContext.getContext().getValueStack().set("cList",cList);
		return "findAll";
	}
	public CategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
