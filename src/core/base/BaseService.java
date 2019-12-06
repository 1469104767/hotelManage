package core.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import core.factory.BeanFactory;
import core.web.Message;

public class BaseService<T,D extends BaseMapper<T>> {
	protected D mapper;
	
	 /**
     * 实例化时写入mapper
     */
	public BaseService() {
        super();
        Class<?> mapperClass;
		try {
			ParameterizedType parametclass = (ParameterizedType) this.getClass().getGenericSuperclass();
	        Type[] actualTypeArguments = parametclass.getActualTypeArguments();
			mapperClass = (Class<?>)actualTypeArguments[1];
			mapper = (D) BeanFactory.getBean(mapperClass.getName(), BaseMapper.class) ;
		} catch (Exception e) {
		}
    }
	
	public T findById(Integer id) {
		return mapper.findById(id);
	}

	public Message save(T entity) {
		mapper.save(entity);
		return Message.success();
	}

	public Message update(T entity) {
		mapper.update(entity);
		return Message.success();
	}

	public List<T> findList(String queryString) {
		return mapper.findList(queryString);
	}

	public Message delete(Integer id) {
		mapper.delete(id);
		return Message.success();
	}
 
}
