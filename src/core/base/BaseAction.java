package core.base;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.factory.BeanFactory;
import core.web.ActionRequest;
import core.web.ActionResponse;
import core.web.Message;

public class BaseAction <T,S extends BaseService<T,?>>{
	protected S service;
	protected HttpServletRequest request = ActionRequest.getInstance();
	protected HttpServletResponse response = ActionResponse.getInstance();
	 /**
    * 实例化时写入service
    */
	@SuppressWarnings("unchecked")
	public BaseAction() {
       super();
       Class<?> serviceClass;
		try {
			serviceClass = Class.forName(this.getClass().getName().replace("Action", "Service").replace("action", "service"));
			service = (S) BeanFactory.getBean(serviceClass.getName(), BaseService.class) ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
   }
	
	/**
	 * 根据id查询
	 */
	public T findById(Integer id) {
		return service.findById(id);
	}
	
	/**
	 * 保存,不需要传id
	 */
	public Message save(T entity) {
		return service.save(entity);
	}
	/**
	 * 更新,必须传id
	 */
	public Message update(T entity) {
		return service.update(entity);
	}
	/**
	 * 根据字段相等查询
	 */
	public List<T> findList(Map<String,String> parMap) {
		StringBuilder queryString = new StringBuilder(" where ");
		for (Entry<String, String> entry : parMap.entrySet()) {
			queryString.append(entry.getKey()).append(" = ").append("'").append(entry.getValue()).append("'").append(" and ");
		}
		return service.findList(queryString.substring(0,queryString.length()-5));
	}
	/**
	 * 根据字段相似查询
	 */
	public List<T> search(Map<String,String> parMap) {
		StringBuilder queryString = new StringBuilder(" where ");
		for (Entry<String, String> entry : parMap.entrySet()) {
			queryString.append(entry.getKey()).append(" like ").append("'%").append(entry.getValue()).append("%'").append(" and ");
		}
		return service.findList(queryString.substring(0,queryString.length()-5));
	}
	/**
	 * 根据id删除
	 */
	public Message delete(Integer id) {
		return service.delete(id);
	}
}
