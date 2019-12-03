package core.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import core.factory.BeanFactory;

public class BaseService<T,D extends BaseDao<T>> {
	protected D dao;
	
	 /**
     * 实例化时写入dao
     */
	public BaseService() {
        super();
        Class<?> daoClass;
		try {
			ParameterizedType parametclass = (ParameterizedType) this.getClass().getGenericSuperclass();
	        Type[] actualTypeArguments = parametclass.getActualTypeArguments();
			daoClass = Class.forName(actualTypeArguments[0].getTypeName().replace("entity", "dao")+"Dao");
			dao = (D) BeanFactory.getBean(daoClass.getName(), BaseDao.class) ;
		} catch (Exception e) {
		}
        
    }
	
	public T findById(Integer id) {
		return dao.findById(id);
	}

	public void save(Object... parameters) throws Exception {
		dao.save(parameters);
	}

	public void update(Object... parameters) throws Exception {
		dao.update(parameters);
	}

	public List<T> findList(String queryString) {
		return dao.findList(queryString);
	}

	public void delete(Integer id) throws Exception {
		dao.delete(id);
	}
 
}
