package com.nd.dao;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nd.entity.Identify;

@Transactional
@Repository
public class IdentifyDao extends BaseDao<Identify>{
	
	/**
	 * 更新验证码，返回0代表更新失败
	 * @param identify
	 * @return
	 */
	public int saveOrUpdate(Identify identify){
		try{
			String nativeSql = "replace into Identify(email,code) values(?,?)";
			SQLQuery query = gs().createSQLQuery(nativeSql);
			query.setParameter(0, identify.getEmail());
			query.setParameter(1, identify.getCode());
			return query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}
