package core.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import core.jdbc.JdbcUtil;

public class BaseDao<T> {
    // 泛型的class
    private Class<T> genericityClass = null;
    
    protected String insert;
    protected String select;
    protected String update;
    protected String delete;
    /**
     * 实例化时写入泛型的class
     */
    public BaseDao() {
        super();
        ParameterizedType parametclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parametclass.getActualTypeArguments();
        genericityClass = (Class<T>) actualTypeArguments[0];
    }
    
    /**
     * <li>方法名：find
     * <li>@param id
     * <li>@return
     * <li>返回类型：T
     * <li>说明：根据id查询
     * <li>创建日期：2019年11月14日
     */
    public T findById(Integer id) {
        return JdbcUtil.selectOne(select + " where id = ?", genericityClass, id);
    }
    
    /**
     * <li>方法名：update
     * <li>@param id
     * <li>@param parameters
     * <li>返回类型：void
     * <li>说明：执行保存操作
     * <li>创建日期：2019年11月14日
     * @throws Exception 
     */
    public void save(Object... parameters) throws Exception {
    	JdbcUtil.update(insert, parameters);
    }
    /**
     * <li>方法名：update
     * <li>@param id
     * <li>@param parameters
     * <li>返回类型：void
     * <li>说明：执行修改操作
     * <li>创建日期：2019年11月14日
     * @throws Exception 
     */
    public void update(Object... parameters) throws Exception {
        JdbcUtil.update(update, parameters);
    }
    /**
     * <li>方法名：update
     * <li>@param id
     * <li>@param parameters
     * <li>返回类型：void
     * <li>说明：查询列表
     * <li>创建日期：2019年11月14日
     */
    public List<T> findList(String queryString) {
        return JdbcUtil.selectList(select + queryString, genericityClass);
    }

    public void delete(Integer id) throws Exception {
        JdbcUtil.update(delete,id);
    }

}