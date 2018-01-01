package shop.category.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import shop.category.categorysecond.vo.CategorySecond;

public class Category implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer cid;
  //一级分类中存放二级分类的集合
  private Set<CategorySecond> categorySeconds=new HashSet<CategorySecond>();
  public Set<CategorySecond> getCategorySeconds() {
	return categorySeconds;
}
public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
	this.categorySeconds = categorySeconds;
}
public Integer getCid() {
	return cid;
}
public void setCid(Integer cid) {
	this.cid = cid;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
private String cname;

public Category(){
	
}
}
