package shop.category.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import shop.category.categorysecond.service.CategorySecondService;
import shop.category.categorysecond.vo.CategorySecond;
import shop.category.service.CategoryService;
import shop.category.vo.Category;
import shop.utils.PageBean;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
 //模型驱动使用的对象
	private CategorySecond categorySecond=new CategorySecond();
//注入二级分类service
	private CategorySecondService categorySecondService;
	//注入一级分类的service
	private CategoryService categoryService;
    
	//接收page
	private Integer page;
	
	//修改二级分类的方法
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
	
	//编辑二级分类的方法
	public String edit(){
		//根据二级分类id查询二级分类的对象
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		//查询所有的一级分类
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	//删除二级分类的方法
	public String delete(){
		//如果要级联删除，先查询，在删除，配置cascade
		System.out.println("执行action");
		categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	//保存二级分类的方法
	public String save(){
		System.out.println(categorySecond);
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	//跳转到添加页面
	public String addPage(){
		//查询所有一级分类
		List<Category> cList=categoryService.findAll();
		System.out.println("cList.get(0):"+cList.get(0));
		//把数据显示到页面的下拉列表
		ActionContext.getContext().getValueStack().set("cList", cList);
	   //页面跳转
		return "addPageSuccess";
	}

	
	//查询二级分类的方法
	public String findAll(){
		PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
		//将pagebean的数据保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
     return "findAll";
	}
	
	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}


	public CategorySecondService getCategorySecondService() {
		return categorySecondService;
	}


	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}



}
