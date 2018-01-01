package shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import shop.product.dao.ProductDao;
import shop.product.vo.Product;
import shop.utils.PageBean;

/**
 * 商品的业务层代码
 * @author Administrator
 *
 */
@Transactional
public class ProductService {
   //注入ProductDao
	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
    //首页上热门商品的查询
	public List<Product> findHot() {
		return productDao.findHot();
	}

	//首页上的最新商品的查询
	public List<Product> findNew() {
		return productDao.fingNew();
		
	}
//根据商品的id查询商品
	public Product findByPid(Integer pid) {
	  return productDao.findByPid(pid);
		
	}
 //根据一级分类的cid,带有分页查询商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//先设置当前的页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit=8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount=0;
		totalCount=productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalPage % limit ==0){
			totalPage=totalCount / limit;
		}else{
			totalPage=totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		//从哪开始
		int begin=(page-1)*limit;
		
		List<Product> list=productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
//根据二级商品id查询商品
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//先设置当前的页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit=8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount=0;
		totalCount=productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalPage % limit ==0){
			totalPage=totalCount / limit;
		}else{
			totalPage=totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		//从哪开始
		int begin=(page-1)*limit;
		
		List<Product> list=productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
//业务层查询商品带分页的方法
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//设置当前的页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit=10;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount=productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		//设置当前显示到页面的数据的集合
		List<Product> list=productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
//业务层保存商品的方法
	public void save(Product product) {
		productDao.save(product);
		
	}
//业务层删除商品的方法
	public void delete(Product product) {
		productDao.delete(product);
	}
//业务层修改商品的方法
	public void update(Product product) {
		productDao.update(product);
		
	}


	
}
