package core.base;

import java.util.List;

import core.annotation.Param;
import core.annotation.Sql;
import core.enumx.SqlType;

public interface BaseMapper<T> {
	
	//需要继承的接口来补充
	String insert = null;
	String delete = null;
	String update = null;
	String select = null;
	String findById = null;
	String findList = null;
    /**
     * <li>方法名：find
     * <li>@param id
     * <li>@return
     * <li>返回类型：T
     * <li>说明：根据id查询
     * <li>创建日期：2019年11月14日
     */
    @Sql(field="findById")
    public T findById(Integer id) ;
    
    /**
     * <li>方法名：update
     * <li>@param entity
     * <li>返回类型：int
     * <li>说明：执行保存操作
     * <li>创建日期：2019年11月14日
     * @throws Exception 
     */
    @Sql(field="insert",type=SqlType.insert)
    public int save(T entity);
    
    /**
     * <li>方法名：update
     * <li>@param entity
     * <li>返回类型：int
     * <li>说明：执行修改操作
     * <li>创建日期：2019年11月14日
     * @throws Exception 
     */
    @Sql(field="update",type=SqlType.update)
    public int update(T entity);
    /**
     * <li>方法名：findList
     * <li>@param queryString
     * <li>返回类型：List<T>
     * <li>说明：查询列表
     * <li>创建日期：2019年11月14日
     */
    @Sql(field="findList")
    public List<T> findList(String queryString);
    
    /**
     * <li>方法名：delete
     * <li>@param id
     * <li>返回类型：int
     * <li>说明：查询列表
     * <li>创建日期：2019年11月14日
     */
    @Sql(field="delete",type=SqlType.delete)
    public int delete(@Param("id")Integer id);

}