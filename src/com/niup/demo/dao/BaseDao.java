package com.niup.demo.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.niup.jdbc.JdbcUtil;

public class BaseDao<T> {
    // 泛型的class
    private Class<T> genericityClass = null;
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
    public T find(int id) {
        String sql = JdbcUtil.getSql(genericityClass, "select") + "where id = ?";
        return JdbcUtil.selectOne(sql, genericityClass, id);
    }

    /**
     * <li>方法名：update
     * <li>@param id
     * <li>@param parameters
     * <li>返回类型：void
     * <li>说明：根据id修改,parameters应该按sql顺序传
     * <li>创建日期：2019年11月14日
     */
    public void update(Object... parameters) {
        String sql = JdbcUtil.getSql(genericityClass, "update");
        JdbcUtil.update(sql, parameters);
    }

    public List<T> findList(String queryString) {
        String sql = JdbcUtil.getSql(genericityClass, "select") + queryString;
        return JdbcUtil.selectList(sql, genericityClass);
    }

    public void delete(int id) {
        String sql = JdbcUtil.getSql(genericityClass, "delete");
        JdbcUtil.update(sql,id);
    }
    public void save(Object... parameters) {
    	String sql = JdbcUtil.getSql(genericityClass, "insert");
    	JdbcUtil.update(sql,parameters);
    }
}