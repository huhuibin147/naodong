package com.nd.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nd.entity.ConcernArticle;
import com.nd.vo.ArticleVO;

@Transactional
@Repository
public class ConcernArticleDao extends BaseDao<ConcernArticle>{
	
	/**
	 * 获取关注文章动态详情
	 * @param userId
	 * @return
	 */
	public List<ArticleVO> getArticleDetail(String userId,Integer page,Integer num){
		String sql = "select a.id,a.uid userId,a.title,concat(left(a.content,180),'...') content,ca.concernDate " +
				"from con_article ca,article a " +
				"where ca.articleId = a.id " +
				"and ca.userId = ? " +
				"order by concernDate desc " +
				"limit ?,?";
		Query query = gs().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(ArticleVO.class));
		query.setParameter(0, userId);
		query.setParameter(1,(page-1)*num);
		query.setParameter(2, num);
		List<ArticleVO> returnList = query.list();
		return returnList;
	}
}
