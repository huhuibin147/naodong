package com.nd.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nd.entity.Question;
import com.nd.utils.PageBean;
import com.nd.vo.TopicVO;

@Transactional
@Repository
public class QuestionDao  extends BaseDao<Question>{
	
	/**
	 * 查询第几页记录
	 * @param page
	 * @return
	 */
	public PageBean QuestionList(int page){
		String hql="from Question ORDER BY zan desc";
		PageBean pb = new PageBean();
		pb = this.queryForPage(hql,null,5,page);
		System.out.println("总页数:"+pb.getTotalPage());
//		for (Question question : list) {
//			System.out.println(question.toString());
//		}
		return pb;
	}
	/**
	 * 搜索
	 * @param page
	 * @param search
	 * @return
	 */
	public PageBean QuestionList(int page,String search){
		String hql="from Question where title like ?  ORDER BY zan desc";
		List v = new ArrayList();
		v.add("%"+search+"%");
		PageBean pb = new PageBean();
		pb = this.queryForPage(hql,v,5,page);
		System.out.println("总页数:"+pb.getTotalPage());
		return pb;
	}
	/**
	 * 话题
	 * @param page
	 * @param topic
	 * @return
	 */
	public PageBean QuestionListByTheme(int page,String topic){
		String hql2="select new com.nd.vo.TopicVO(q.id,q.title,q.content,q.time,q.zan,q.anum,u.name,u.img) FROM Theme as t,Question as q,User as u where t.id=q.tid and q.uid=u.id and t.theme=? ";
		PageBean pb = new PageBean();
		List<String> values = new ArrayList<>();
		values.add(topic);
		pb = this.queryForPage(hql2,values,5,page);
		List<TopicVO> list = pb.getList();
		System.out.println("总页数:"+pb.getTotalPage());
		return pb;
	}
	
	/**
	 * 获取用户的问题
	 */
	public List<Question> loadByUserId(String userId,Integer page,Integer num){
		String sql = "select id,title,concat(left(content,180),'...') content,uid,time,zan,anonymity,anum,tid,modifyDate "
				+ "from question where uid = ? "
				+ "order by time desc "
				+ "limit ?,?";
		Query query = gs().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Question.class));
		query.setParameter(0, userId);
		query.setParameter(1, (page-1)>0?(page-1)*num:0);
		query.setParameter(2, num>0?num:0);
		return query.list();
	}
	
	
}
