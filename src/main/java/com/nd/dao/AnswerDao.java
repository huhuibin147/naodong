package com.nd.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nd.entity.Answer;
import com.nd.entity.Article;
import com.nd.utils.PageBean;
import com.nd.vo.AnswerVO;

@Transactional
@Repository
public class AnswerDao extends BaseDao<Answer>{
	/**
	 * 问题的回答
	 * @param page
	 * @param qid
	 * @return pageBean
	 */
	public PageBean AnswerList(int page,int qid){
		String hql="from Answer where qid=?";
		List<Object> values=new ArrayList<>();
		values.add(qid);
		PageBean pb = new PageBean();
		pb = this.queryForPage(hql,values,5,page);
		List<Answer> list = pb.getList();
		System.out.println("总页数:"+pb.getTotalPage());
		return pb;
	}
	/**
	 * 返回问题的回答数
	 * @param qid
	 * @return
	 */
	public Integer getCount(int qid){
		String hql="select count(*) from Answer where qid='"+qid+"'";
		return Integer.parseInt(gs().createQuery(hql).list().get(0).toString());
	}
	
	/**
	 * 获取用户的回答
	 */
	public List<AnswerVO> loadByUserId(String userId,Integer page,Integer num){
		String sql = "select answer.id id,title,answer.uid uid,concat(left(answer.content,180),'...') content,answer.anonymity,answer.hit,answer.time "
				+ "from answer,question "
				+ "where answer.uid = ? "
				+ "and question.id = answer.qid "
				+ "order by time desc "
				+ "limit ?,?";
		Query query = gs().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(AnswerVO.class));
		query.setParameter(0, userId);
		query.setParameter(1, (page-1)>0?(page-1)*num:0);
		query.setParameter(2, num>0?num:0);
		return query.list();
	}
}
