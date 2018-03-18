package cn.hd.common.util;


import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XRog
 * On 2/2/2017.2:28 PM
 * 持久层工具方法
 */
@Repository
@Qualifier("baseDao")
public class BasicImplDao<T> implements BasicDao<T> {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     *
     * @param sql SQL语句
     * @param page 要显示第几页
     * @param rows 每页显示多少条
     * @return
     */
    @Override
    public List findBySql(String sql, int page, int rows) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    /**
     *
     * @param hql HQL语句
     * @param page  要显示第几页
     * @param rows 每页显示多少条
     * @return
     */
    @Override
    public List<T> find(String hql, int page, int rows) {
        Query q = getCurrentSession().createQuery(hql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public T getByHql(String hql) {
        Query q = getCurrentSession().createQuery(hql);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @Override
    public List<T> findByCacheable(String hql) {
        Query q = getCurrentSession().createQuery(hql);
        return q.setCacheable(true).list();
    }

    @Override
    public List<T> find(String hql) {
        Query q = getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public List findBySql(String sql) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        return q.list();
    }

    @Override
    public Long count(String hql) {
        Query q = getCurrentSession().createQuery(hql);
        return (Long)q.uniqueResult();
    }

    @Override
    public double doubleCount(String hql) {
        Query q = getCurrentSession().createQuery(hql);
        return Double.valueOf(q.uniqueResult().toString());
    }

    @Override
    public BigInteger countBySql(String sql) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        return (BigInteger) q.uniqueResult();
    }

    @Override
    public BigInteger countBySql(String sql, Map params) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        return (BigInteger) q.uniqueResult();
    }

    @Override
    public int executeHql(String hql) {
        Query q = getCurrentSession().createQuery(hql);
        return q.executeUpdate();
    }

    @Override
    public int executeSql(String sql) throws Exception {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        return q.executeUpdate();
    }


    @Override
    public void flush() {
        getCurrentSession().flush();
    }

    @Override
    public void saveOrUpdate(Object o) throws Exception {
        if (o != null) {
            getCurrentSession().saveOrUpdate(o);
        }
    }

    @Override
    public void update(Object o) throws Exception {
        if (o != null) {
            getCurrentSession().update(o);
        }
    }

    @Override
    public void delete(Object o) throws Exception {
        if (o != null) {
            getCurrentSession().delete(o);
        }
    }


    @Override
    public void preCallSql(final Map map) throws Exception {
        hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                List<Object> information = new ArrayList<Object>();
                ResultSet rs = null;
                CallableStatement proc = null;
                try { // 查询需要统计的项目
                    proc = SessionFactoryUtils.getDataSource(sessionFactory).getConnection().prepareCall(map.get("callSql").toString());
                    proc.setInt(1, Integer.valueOf(String.valueOf(map.get("param1"))));// "510100"
                    proc.execute();
                } catch (HibernateException e) {
                    throw e;
                } catch (SQLException e) {
                    throw e;
                } finally {
                    try {
                        if (rs != null)
                            rs.close();
                    } catch (Exception e) {
                    }
                    try {
                        if (proc != null)
                            proc.close();
                    } catch (Exception e) {
                    }
                    try {
                        if (session != null)
                            session.disconnect();
                    } catch (Exception e) {
                    }
                }
                return information;
            }
        });
    }

    @Override
    public int executeSql(String sql, Map<String, Object> params) throws Exception {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    @Override
    public int executeHql(String hql, Map<String, Object> params) throws Exception {
        Query q = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    @Override
    public Long count(String hql, Map<String, Object> params) {
        Query q = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (Long)q.uniqueResult();
    }

    @Override
    public List findBySql(String sql,  Map<String, Object> params, Class<T> classes) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql).addEntity(classes);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.list();
    }

    @Override
    public List findBySql(String sql, Class<T> classes) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql).addEntity(classes);
        return q.list();
    }

    @Override
    public List findBySql(String sql, Map<String,Object> params) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.list();
    }

    @Override
    public List<T> find(String hql,  Map<String, Object> params){
        Query q = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.list();
    }

    @Override
    public T getByHql(String hql, Map<String, Object> params){
        Query q = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @Override
    public List<T> findByCacheable(String hql, Map<String, Object> params) {
        Query q = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setCacheable(true).list();
    }
    @Override
    public T getById(Class c, Serializable id) {
        return (T) getCurrentSession().get(c, id);
    }

    @Override
    public List findBySql(String sql, Map<String,Object> params, int page, int rows, Class<T> classes) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql).addEntity(classes);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public List findBySql(String sql, int page, int rows, Class<T> classes){
        SQLQuery q = getCurrentSession().createSQLQuery(sql).addEntity(classes);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public List findBySql(String sql, Map<String, Object> params, int page, int rows) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params, int page, int rows){
        Query q = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }
    @Override
    public Serializable save(Object entity) throws Exception{
        if (entity != null) {
            return getCurrentSession().save(entity);
        }
        return null;
    }
}