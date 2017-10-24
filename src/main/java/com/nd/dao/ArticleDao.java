package com.nd.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nd.entity.Article;
import com.nd.entity.ArticleFind;
import com.nd.utils.PageBean;

@Transactional
@Repository
public class ArticleDao extends BaseDao<Article>{
	
	/**
	 * 查询第几页记录
	 * @param page
	 * @return
	 */
	public PageBean ArtList(int page){
		String hql="from Article";
		PageBean pb = new PageBean();
		pb = this.queryForPage(hql,null,5,page);
		List<Article> list = pb.getList();
		return pb;
	}
	
	public List<Article> loadByUserId(String userId,Integer page,Integer num){
		String sql = "select id,uid,concat(left(content,180),'...') content,title,zan,time "
				+ "from article where uid = ? "
				+ "order by time desc "
				+ "limit ?,?";
		Query query = gs().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Article.class));
		query.setParameter(0, userId);
		query.setParameter(1, (page-1)>0?(page-1)*num:0);
		query.setParameter(2, num>0?num:0);
		return query.list();
	}
	
	public List hotList(){
		String hql="select new com.nd.entity.ArticleFind(a.id,u.name,a.content,a.title,a.zan) FROM User as u,Article as a where a.uid=u.id order by a.zan desc";
		PageBean pb = new PageBean();
		pb = this.queryForPage(hql,null,5,1);
		List<ArticleFind> list = pb.getList();
		return list;
	}
}
