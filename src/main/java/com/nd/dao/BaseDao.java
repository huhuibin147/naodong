 package com.nd.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.nd.utils.PageBean;



@Transactional
public class BaseDao<T> {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session gs() {
		return this.sessionFactory.getCurrentSession();
	}
	/*
	 * 根据T得到真正的class类型，使用反射
	 */
    protected Class<T> entityClass;

	protected Class getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public T get(Integer id){
		T t = (T) gs().get(getEntityClass(), id);
		return t;
	}
	/**
	 * 添加
	 * @param t
	 */
	public int add(T t){
		return (int)gs().save(t);
	}
	
	/**
	 * 更新
	 * @param t
	 */
	public void update(T t){
		gs().update(t);
	}
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		gs().delete(id);
	}
	/**
	 * 查询全部
	 * @return
	 */
	public List<T> getAll(){
		return gs().createCriteria(getEntityClass()).list();
	}
	/**
	 * HQL查询
	 * @param hqlString
	 * @param values
	 * @return
	 */
	public List<T> getListByHQL(final String hqlString,final List values){
		Query query = gs().createQuery(hqlString);
		if(values != null){
			Object[] vvs = values.toArray();
			for (int i =0; i < vvs.length; i++) {
				query.setParameter(i, vvs[i]);
			}
		}
		return query.list();
	}
	
	
	/**
	 * 执行复杂的sql查询 
	 * 得到一列：List<Object>
	 * 得到多列：List<Object[]>
	 * @param sqlString
	 * @param values
	 * @return
	 */
	@Transactional(readOnly = true)
	public List getListBySQL(final String sqlString, final List values) {
		Query query = gs().createSQLQuery(sqlString);
		if (values != null) {
			Object[] vvs = values.toArray(new Object[values.size()]);
			for (int i = 0; i < vvs.length; i++) {
				query.setParameter(i, vvs[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * 分页
	 * @param hql
	 * @param values
	 * @param offset
	 * @param length
	 * @return
	 */
	public List queryByOffset(final String hql,final List values,int offset,int length){
		Query query = gs().createQuery(hql);
		if(values != null){
			Object[] vvs = values.toArray();
			for (int i =0; i < vvs.length; i++) {
				query.setParameter(i, vvs[i]);
			}
		}
		query.setFirstResult(offset);
		query.setMaxResults(length);
		return query.list();
	}
	
	/**
	 * 查询总记录数  Entity是实体名字
	 * @param Entity
	 * @return
	 */
	public int getCount(String Entity){
		String hql = "select count(*) from "+Entity;
		Query q = gs().createQuery(hql);
		return Integer.parseInt(q.list().get(0).toString());
	}
	
	/**
	 * 用于关注查询
	 */
	public List getListByUserId(String userId){
		String name = getEntityClass().getName();
		name = name.substring(name.lastIndexOf(".")+8, name.length()).toLowerCase();
		String sql = "select * from con_"+name+" where userId = "+userId+" order by concernDate desc";
		Query q = gs().createSQLQuery(sql);;
		return q.list();
	}
	
	/**
	 * 通用hql查询，根据用户编号查询符合条件的所有数据
	 * @param clz
	 * @param userId
	 * @return
	 */
	public List<?> loadByUserId(String userId,Integer page,Integer num){
		String hql = "from :clz where userId = :userId limit :startRow,:rowNum";
		Query query = gs().createQuery(hql);
		query.setParameter("clz", getEntityClass());
		query.setParameter("userId", userId);
		query.setParameter("startRow", (page-1)*num);
		query.setParameter("rowNum", num);
		return query.list();
	}
	

	
	/**
	 * 查询结果数
	 * @param hql
	 * @param values
	 * @return
	 */
	public int getCount(final String hql,final List values){
		return this.getListByHQL(hql, values).size();
	}
	/**
	 * 分页查询 
	 * @param hql
	 * @param values 可为null
	 * @param PageSize 每页记录数
	 * @param page 第几页
	 * @return
	 */
	public PageBean queryForPage(final String hql,final List values,int PageSize,int page){
		PageBean pb = new PageBean();
		int allRow = this.getCount(hql, values);
		int offset = PageBean.countOffset(PageSize, page);
		List list = this.queryByOffset(hql, values, offset, PageSize);
		pb.setAllRow(allRow);
		pb.setTotalPage(PageBean.countTotalPage(PageSize, allRow));
		pb.setPageSize(PageSize);
		pb.setCurrentPage(PageBean.countCurrentPage(page));
		pb.setList(list);
		pb.init();
		return pb;
	}
	
}
