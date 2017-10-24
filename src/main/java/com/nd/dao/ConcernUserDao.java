package com.nd.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nd.entity.ConcernUser;
import com.nd.vo.ConcernNumVO;

@Transactional
@Repository
public class ConcernUserDao extends BaseDao<ConcernUser>{
	
//	public List<UserVO> getConcernUserDetail(String userId){
//		String sql = "select user.userName, from con_user conUser,user "
//				   + "where conUser.con_userId = userId "
//				   + "  and ";
//	} 
	
	public ConcernNumVO getUserAllConcernNum(String userId){
		String sql = "select " +
				"(select count(1) from con_user c where c.userId = :userId) concernManNum," +
				"(select count(1) from con_user c1 where c1.con_userId = :userId) concernMeNum," +
				"(select count(1) from con_theme c2 where c2.userId = :userId ) concernThemeNum , " +
				"(select count(1) from con_article c3 where c3.userId = :userId) concernArticleNum," +
				"(select count(1) from con_question c4 where c4.userId = :userId) concernQuestionNum," +
				"(select count(1) from con_answer c5 where c5.userId = :userId) concernAnswerNum ";
		Query query = gs().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(ConcernNumVO.class));
		query.setParameter("userId", userId);
		List<ConcernNumVO> list = query.list() ;
		if(list.isEmpty())
			return new ConcernNumVO();
		else{
			return list.get(0);
		}
		
	}
	
	/**
	 * 取消关注用户
	 * @param userId
	 * @param concernId
	 * @return
	 */
	public boolean deleteConcernUser(String userId,String concernId){
		String sql = "delete from con_user where userId = :userId and con_userId = :concernId ";
		Query query = gs().createSQLQuery(sql);
		query.setParameter("userId", userId);
		query.setParameter("concernId", concernId);
		if(query.executeUpdate()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 取消关注话题
	 * @param userId
	 * @param concernId
	 * @return
	 */
	public boolean deleteConcernTheme(String userId,String concernId){
		String sql = "delete from con_theme where userId = :userId and themeId = :concernId ";
		Query query = gs().createSQLQuery(sql);
		query.setParameter("userId", userId);
		query.setParameter("concernId", concernId);
		if(query.executeUpdate()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 取消关注问题
	 * @param userId
	 * @param concernId
	 * @return
	 */
	public boolean deleteConcernQuestion(String userId,String concernId){
		String sql = "delete from con_question where userId = :userId and questionId = :concernId ";
		Query query = gs().createSQLQuery(sql);
		query.setParameter("userId", userId);
		query.setParameter("concernId", concernId);
		if(query.executeUpdate()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 取消关注文章
	 * @param userId
	 * @param concernId
	 * @return
	 */
	public boolean deleteConcernArticle(String userId,String concernId){
		String sql = "delete from con_article where userId = :userId and articleId = :concernId ";
		Query query = gs().createSQLQuery(sql);
		query.setParameter("userId", userId);
		query.setParameter("concernId", concernId);
		if(query.executeUpdate()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 取消关注回答
	 * @param userId
	 * @param concernId
	 * @return
	 */
	public boolean deleteConcernAnswer(String userId,String concernId){
		String sql = "delete from con_answer c where userId = :userId and answerId = :answerId ";
		Query query = gs().createSQLQuery(sql);
		query.setParameter("userId", userId);
		query.setParameter("concernId", concernId);
		if(query.executeUpdate()>0){
			return true;
		}else{
			return false;
		}
	}
	
}
