package com.nd.utils;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Component
@Transactional
public class DBUtil {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session gs() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public List<?> loadByUserIdSuper(Class clz,String userId,Integer page,Integer num) throws Exception{
		String tableName = StringUtils.uncapitalize(clz.getSimpleName());
		String sql = "select * from "+tableName
				   + " where uid = ? "
				   + " limit ?,? ";
		Query query = gs().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clz));
		query.setParameter(0, Integer.parseInt(userId));
		query.setParameter(1, (page-1)*num);
		query.setParameter(2, num);
		return query.list(); 
	}
	
	public List<?> loadByParam(Class clz,String paramName,String paramValue,Integer page,Integer num) throws Exception{
		String tableName = StringUtils.uncapitalize(clz.getSimpleName());
		String sql = "select * from "+tableName
				   + " where "+paramName+" = ? "
				   + " limit ?,? ";
		Query query = gs().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clz));
		query.setParameter(0, Integer.parseInt(paramValue));
		query.setParameter(1, (page-1)*num);
		query.setParameter(2, num);
		return query.list(); 
	}
}	
