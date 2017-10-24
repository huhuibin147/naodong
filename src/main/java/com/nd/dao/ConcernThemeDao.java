package com.nd.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nd.entity.ConcernTheme;
import com.nd.vo.ThemeVO;

@Transactional
@Repository
public class ConcernThemeDao extends BaseDao<ConcernTheme>{
	
	/**
	 * 获取关注主题信息
	 * @param userId
	 * @return
	 */
	public List<ThemeVO> getThemesDetail(String userId,Integer page,Integer num){
		String sql  = "select theme.id,user.username owner,theme.theme themeName,theme.intro intro,theme.img imgSrc,con_theme.concernDate concernDate "
					+ "from theme ,user ,con_theme "
					+ "where con_theme.userId = ? "
					+ "and con_theme.userId = user.id "
					+ "and con_theme.themeId = theme.id "
					+ "order by concernDate desc "
					+ "limit ?,?";
		Query query = gs().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(ThemeVO.class));
		query.setParameter(0, userId);
		query.setParameter(1,(page-1)*num);
		query.setParameter(2, num);
		List<ThemeVO> returnList = query.list();
		return returnList;
	}
	/**
	 * 判断是否关注  
	 * @param tid
	 * @param uid
	 * @return 0-已关注   1-未关注
	 */
	public int exitConcerTopic(int tid,int uid){
		String sql="select id from con_theme where themeId=? and userId=?";
		List values=new ArrayList();
		values.add(tid);
		values.add(uid);
		List l = this.getListBySQL(sql, values);
		if(l.isEmpty()){
			return 1;
		}
		return 0;
	}
	public int getTidByTopic(String topic){
		String sql="select id from theme where theme=?";
		List v=new ArrayList();
		v.add(topic);
		return (int) this.getListBySQL(sql, v).get(0);
	}
}
