package com.sq.fs.dao.impl;

import com.sq.fs.dao.BaseDao;
import com.sq.fs.pojo.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 通用DAO实现
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;


	private Class clazz;//用于接收运行期泛型类型
	protected String className;//类名
	protected String keyName;//主键oid
	protected String defaultOrderColum;//默认排序列
	protected String defaultOrderType;//默认排序规则（正序还是倒序）

	protected abstract void setClassName();
	protected abstract void setKeyName();
	protected abstract void setDefaultOrderColum();
	protected abstract void setDefaultOrderType();

	public BaseDaoImpl() {
		//获得当前类型的带有泛型类型的父类
		ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得运行期的泛型类型
		clazz = (Class) ptClass.getActualTypeArguments()[0];
	}

	protected Session currentSession(){
		return sessionFactory.getCurrentSession();
	}


	@Override
	public Serializable save(T o) {
		return currentSession().save(o);
	}  
	
	@Override
	public void update(T o) {  
		currentSession().update(o);  
	}  
	
	@Override
	public void saveOrUpdate(T o) {  
		currentSession().saveOrUpdate(o);  
	}  

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T o) {
		return (T) currentSession().merge(o);  
	}  
	
	@Override
    public void deleteById(Serializable id) {
		T o = this.getById(id);
		currentSession().delete(o); 
	}
    
	@Override
	public void delete(T o) {  
		currentSession().delete(o);  
	}   
	
	@Override
	public Integer deleteBatch(Serializable[] ids) {
		Query<T> q = currentSession().createQuery(
						" delete from " + className + " where " + keyName + " in (:ids)");  
		q.setParameterList("ids", ids);
		return q.executeUpdate();  
	}
	
	@Override
	public Integer deleteBatch(List<Serializable> ids) {
		Query<T> q = currentSession().createQuery(
						" delete from " + className + " where " + keyName + " in (:ids)");  
		q.setParameterList("ids", ids);
		return q.executeUpdate();  
	}
	
	@Override
	public T getById(Serializable id) {  
		return (T) currentSession().get(clazz, id);  
	}  
	
	@Override
	public T get(String hql) {  
		List<T> l = this.find(hql);  
		if (l != null && l.size() > 0) {  
			return l.get(0);  
		} else {  
			return null;  
		}  
	}  

	@Override
	public T get(String hql, Object[] param) {  
		List<T> l = this.find(hql, param);  
		if (l != null && l.size() > 0) {  
			return l.get(0);  
		} else {  
			return null;  
		}  
	}  

	@Override
	public T get(String hql, List<Object> param) {  
		List<T> l = this.find(hql, param);  
		if (l != null && l.size() > 0) {  
			return l.get(0);  
		} else {  
			return null;  
		}  
	}  
	
	@Override
	public List<T> find(String hql) {  
		return currentSession().createQuery(hql, clazz).list();
	}  

	@Override
	public List<T> find(String hql, Object[] param) {  
		Query<T> q = currentSession().createQuery(hql, clazz);  
		if (param != null && param.length > 0) {  
			for (int i = 0; i < param.length; i++) {  
				q.setParameter(i, param[i]);  
			}  
		}  
		return q.list();  
	}  

	@Override
	public List<T> find(String hql, List<Object> param) {  
		Query<T> q = currentSession().createQuery(hql, clazz);  
		if (param != null && param.size() > 0) {  
			for (int i = 0; i < param.size(); i++) {
					q.setParameter(i, param.get(i));
				}
		}  
		return q.list();  
	}  
	
	@Override
	public List<T> find(String hql, Integer start, Integer rows){
		if (start == null || start < 0) {  
			start = 0;  
		}  
		if (rows == null || rows < 1) {  
			rows = 10;  
		}  
		Query<T> q = currentSession().createQuery(hql, clazz);  
		return q.setFirstResult(start).setMaxResults(rows).list();  
	}
	  
	@Override
	public List<T> find(String hql, Object[] param, Integer start, Integer rows) {  
		if (start == null || start < 0) {  
			start = 0;  
		}  
		if (rows == null || rows < 1) {  
			rows = 10;  
		}  
		Query<T> q = currentSession().createQuery(hql, clazz);  
		if (param != null && param.length > 0) {  
			for (int i = 0; i < param.length; i++) {  
				q.setParameter(i, param[i]);  
			}  
		}  
		return q.setFirstResult(start).setMaxResults(rows).list();  
	}  
	
	@Override
	public List<T> find(String hql, List<Object> param, Integer start, Integer rows) {  
		if (start == null || start < 0) {  
			start = 0;  
		}  
		if (rows == null || rows < 1) {  
			rows = 10;  
		}  
		Query<T> q = currentSession().createQuery(hql, clazz);  
		if (param != null && param.size() > 0) {  
			for (int i = 0; i < param.size(); i++) {  
				q.setParameter(i, param.get(i));  
			}  
		}  
		return q.setFirstResult(start).setMaxResults(rows).list();  
	}  
	
	@Override
	public List<T> find(Integer start, Integer rows){
		if (start == null || start < 0) {  
			start = 0;  
		}  
		if (rows == null || rows < 1) {  
			rows = 10;  
		}  
		Query<T> q = currentSession().createQuery("from  " + className + " t order by t." + defaultOrderColum + " " + defaultOrderType, clazz);
		return q.setFirstResult(start).setMaxResults(rows).list();  
	}
	
	@Override
	public List<T> findAll() {
		System.out.println("from  " + className + " t order by t." + defaultOrderColum + " " + defaultOrderType);
		return currentSession().createQuery("from  " + className + " t order by t." + defaultOrderColum + " " + defaultOrderType, clazz).list();
	}  
	
	@Override
	public List<T> nativeFind(String sql) {
		NativeQuery<T> sqlQuery = currentSession().createNativeQuery(sql, clazz);
		return sqlQuery.list();
	} 
	
	@Override
	public List<T> nativeFind(String sql, Map<String, Object> param) {
		NativeQuery<T> sqlQuery = currentSession().createNativeQuery(sql, clazz);
		if (param != null && param.size() > 0) {  
			Set<String> keySet = param.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				sqlQuery.setParameter(key, param.get(key));  
			}
		}  
		return sqlQuery.list();
	} 

	@Override
	public Object uniqueResult(String hql) {  
		return currentSession().createQuery(hql).uniqueResult();  
	}  

	@Override
	public Object uniqueResult(String hql, Object[] param) {  
		Query<T> q = currentSession().createQuery(hql);  
		if (param != null && param.length > 0) {  
			for (int i = 0; i < param.length; i++) {  
				q.setParameter(i, param[i]);  
			}  
		}  
		return q.uniqueResult();  
	}  

	@Override
	public Object uniqueResult(String hql, List<Object> param) {  
		Query<T> q = currentSession().createQuery(hql);  
		if (param != null && param.size() > 0) {  
			for (int i = 0; i < param.size(); i++) {  
				q.setParameter(i, param.get(i));  
			}  
		}  
		return q.uniqueResult();  
	}  
	
	@Override
	public Object count() {  
		return currentSession().createQuery("select count(1) from " + className).uniqueResult();  
	}  
	
	@Override
	public Integer executeHql(String hql) {  
		return currentSession().createQuery(hql).executeUpdate();  
	}  

	@Override
	public Integer executeHql(String hql, Object[] param) {  
		Query<T> q = currentSession().createQuery(hql);  
		if (param != null && param.length > 0) {  
			for (int i = 0; i < param.length; i++) {  
				q.setParameter(i, param[i]);  
			}  
		}  
		return q.executeUpdate();  
	}  

	@Override
	public Integer executeHql(String hql, List<Object> param) {  
		Query<T> q = currentSession().createQuery(hql);  
		if (param != null && param.size() > 0) {  
			for (int i = 0; i < param.size(); i++) {  
				q.setParameter(i, param.get(i));  
			}  
		}  
		return q.executeUpdate();  
	}
	
	@Override
	public Integer executeSql(String sql) {  
		return currentSession().createNativeQuery(sql).executeUpdate();  
	}
	
	@Override
	public Integer executeSql(String sql, Map<String, Object> param) {  
		Query<T> q = currentSession().createNativeQuery(sql);  
		if (param != null && param.size() > 0) {  
			Set<String> keySet = param.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				q.setParameter(key, param.get(key));  
			}
		}  
		return q.executeUpdate();  
		//旧版本的不建议使用，上面新版本的必须用名字传参，不能用索引
		/*Query q = currentSession().createSQLQuery(sql);  
		if (param != null && param.length > 0) {  
			for (int i = 0; i < param.length; i++) {  
				q.setParameter(i, param[i]);  
			}  
		}  
		return q.executeUpdate(); */
	}  

	
	@Override
	public ProcedureCall callProc(String name) {
		return currentSession().createStoredProcedureCall(name);
	}
}  	