package cn.hd.common.util;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by XRog
 * On 2/2/2017.2:28 PM
 * 持久层的工具接口
 *
 */
public interface BasicDao<T>{
    /*******************[old]**********************/
    Serializable save(T entity) throws Exception;
    /*******************[old]**********************/
    /*********************[ page plugin ]***********************/
    /**
     * 获得结果集
     * @param sql SQL语句
     * @param page 要显示第几页
     * @param rows 每页显示多少条
     * @return 结果集
     */
    @SuppressWarnings("rawtypes")
    public List findBySql(String sql, int page, int rows) ;
    /**
     * 获得分页后的对象列表
     *
     * @param hql HQL语句
     * @param page  要显示第几页
     * @param rows 每页显示多少条
     * @return List
     */
    public List<T> find(String hql, int page, int rows);
    /**
     * 获得分页后的对象列表
     *
     * @param hql HQL语句
     * @param params 参数
     * @param page 要显示第几页
     * @param rows 每页显示多少条
     * @return List
     */
    public List<T> find(String hql, Map<String, Object> params, int page, int rows);
    /**
     *
     * @param sql
     * @param params
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    List findBySql(String sql, Map<String, Object> params, int page, int rows);
    /**
     * 获得结果集
     * @param sql SQL语句
     * @param page 要显示第几页
     * @param rows 每页显示多少条
     * @return 结果集
     */
    @SuppressWarnings("rawtypes")
    public List findBySql(String sql, int page, int rows, Class<T> classes) ;
    /**
     * 获得结果集
     *
     * @param sql SQL语句
     * @param params 参数
     * @param page 要显示第几页
     * @param rows 每页显示多少条
     * @return 结果集
     */
    @SuppressWarnings("rawtypes")
    public List findBySql(String sql, Map<String, Object> params, int page, int rows, Class<T> classes) ;
    /*********************[ page plugin ]***********************/
    /*********************[ query plugin ]***********************/
    /**
     * 通过主键获得对象
     * @param c 类名.class
     * @param id 主键
     * @return 对象
     */
    public T getById(Class<T> c, Serializable id);
    /**
     * 通过HQL语句获取一个对象
     * @param hql HQL语句
     * @return 对象
     */
    public T getByHql(String hql);
    /**
     * 获得对象列表(缓存查询)
     * @param hql HQL语句
     * @return List
     */
    public List<T> findByCacheable(String hql);
    /**
     * 获得对象列表
     * @param hql HQL语句
     * @return List
     */
    List<T> find(String hql);
    /**
     * 获得结果集
     * @param sql SQL语句
     * @return 结果集
     */
    @SuppressWarnings("rawtypes")
    public List findBySql(String sql);
    /**
     * 获得对象列表 缓存查询
     *
     * @param hql HQL语句
     * @param params 参数
     * @return List
     */
    public List<T> findByCacheable(String hql, Map<String, Object> params);
    /**
     * 通过HQL语句获取一个对象
     *
     * @param hql HQL语句
     * @param params 参数
     * @return 对象
     */
    public T getByHql(String hql, Map<String, Object> params);
    /**
     * 获得对象列表
     *
     * @param hql  HQL语句
     * @param params 参数
     * @return List
     */
    public List<T> find(String hql, Map<String, Object> params);
    /**
     *
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    List findBySql(String sql, Map<String, Object> params);
    /**
     * 获得结果集
     * @param sql SQL语句
     * @return 结果集
     */
    @SuppressWarnings("rawtypes")
    public List findBySql(String sql, Class<T> classes);
    /**
     * 获得结果集
     * @param sql SQL语句
     * @param params 参数
     * @return 结果集
     */
    @SuppressWarnings("rawtypes")
    public List findBySql(String sql, Map<String, Object> params, Class<T> classes) ;
    /*********************[ query plugin ]***********************/
    /*********************[ count plugin ]***********************/
    /**
     * 统计数目
     * @param hql HQL语句(select count(*) from T)
     * @return long
     */
    public Long count(String hql);
    public double doubleCount(String hql);
    /**
     * 统计数目
     * @param hql HQL语句(select count(*) from T where xx = :xx)
     * @param params 参数
     * @return long
     */
    public Long count(String hql, Map<String, Object> params);
    /*********************[ count plugin ]***********************/
    /*********************[ execute plugin ]***********************/
    /**
     * 执行一条HQL语句
     * @param hql HQL语句
     * @return 响应结果数目
     */
    public int executeHql(String hql);
    /**
     * 执行SQL语句
     * @param sql SQL语句
     * @return 响应行数
     */
    public int executeSql(String sql) throws Exception;
    /**
     * 执行一条HQL语句
     * @param hql HQL语句
     * @param params  参数
     * @return 响应结果数目
     */
    public int executeHql(String hql, Map<String, Object> params) throws Exception;
    /**
     * 执行SQL语句
     * @param sql SQL语句
     * @param params 参数
     * @return 响应行数
     */
    public int executeSql(String sql, Map<String, Object> params) throws Exception;
    /**
     *
     * @param map
     * @throws Exception
     */
    public void preCallSql(final Map<String, Object> map) throws Exception;
    /*********************[ execute plugin ]***********************/
    /*********************[ count plugin ]***********************/
    /**
     * 统计
     * @param sql  SQL语句
     * @return 数目
     */
    public BigInteger countBySql(String sql);
    /**
     * 统计
     * @param sql SQL语句
     * @param params 参数
     * @return 数目
     */
    public BigInteger countBySql(String sql, Map<String, Object> params);
    /*********************[ count plugin ]***********************/
    /*********************[ delete update saveOrUpdate plugin ]***********************/
    /**
     * 删除一个对象
     *
     * @param o 对象
     */
    public void delete(T o) throws Exception;
    /**
     * 更新一个对象
     * @param o 对象
     */
    public void update(T o) throws Exception;
    /**
     * 保存或更新一个对象
     * @param o 对象
     */
    public void saveOrUpdate(T o) throws Exception;
    /*********************[ delete update saveOrUpdate plugin ]***********************/
    void flush();
}