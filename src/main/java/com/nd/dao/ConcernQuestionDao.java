package com.nd.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nd.entity.ConcernQuestion;
import com.nd.vo.QuestionVO;

@Transactional
@Repository
public class ConcernQuestionDao extends BaseDao<ConcernQuestion>{
	
	/**
	 * 获取关注问题动态详情
	 * @param userId
	 * @return
	 */
	public List<QuestionVO> getQuestionDetail(String userId,Integer page,Integer num){
		String sql = "select q.id,q.title,concat(left(q.content,180),'...') content,q.modifyDate,q.time,cq.concernDate,u.`name` from con_question cq,question q,user u "
				   + "where cq.questionId = q.id "
				   + "and q.uid = u.id "
				   + "and cq.userId = ? "
				   + "order by concernDate desc "
				   + "limit ?,?";
		Query query = gs().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(QuestionVO.class));
		query.setParameter(0, userId);
		query.setParameter(1,(page-1)*num);
		query.setParameter(2, num);
		List<QuestionVO> returnList = query.list();
		return returnList;
	}
	
	/**
	 * 判断是否关注  
	 * @param qid
	 * @param uid
	 * @return  0-已关注   1-未关注
	 */
	public int exitConcerQues(int qid,int uid){
		String sql="select id from con_question where questionId=? and userId=?";
		List values=new ArrayList();
		values.add(qid);
		values.add(uid);
		List l = this.getListBySQL(sql, values);
		if(l.isEmpty()){
			return 1;
		}
		return 0;
	}
	
}
