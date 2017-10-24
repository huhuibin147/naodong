package com.nd.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nd.entity.Theme;
import com.nd.utils.PageBean;
import com.nd.vo.AnswerVO;

@Transactional
@Repository
public class ThemeDao extends BaseDao<Theme>{
	
	/**
	 * 查看主题id
	 * @param topic
	 * @return 主题id  不存在则返回-1
	 */
	public Integer getThemeId(String topic){
		String hql = "from Theme where theme=?";
		List<String> values = new ArrayList<>();
		values.add(topic);
		List<Theme> list = this.getListByHQL(hql, values);
		
		if(list.size()!=0){
			return list.get(0).getId();
		}else{
			return -1;
		}
		
	}
	/**
	 * 获得话题
	 * @param tid
	 * @return
	 */
	public String getThemeById(int tid){
		String hql = "from Theme where id=?";
		List<Integer> values = new ArrayList<>();
		values.add(tid);
		return this.getListByHQL(hql, values).get(0).getTheme();
	}
	/**
	 * 获得用户关注的话题
	 * @param email
	 * @return
	 */
	public List getThemeByEmail(String email){
		String sql = "SELECT t.theme from user as u,con_theme as c_t,theme as t where u.id=c_t.userId AND t.id=c_t.themeId and email = ?";
		List<String> values = new ArrayList<>();
		values.add(email);
		return this.getListBySQL(sql, values);
	}
	
	/**
	 * 分页方法
	 * @param page
	 * @param topic
	 * @return
	 */
	public PageBean ThemeList(int page,String topic){
		String hql="from Theme where theme=?";
		List<String> values = new ArrayList<>();
		values.add(topic);
		PageBean pb = new PageBean();
		pb = this.queryForPage(hql,values,5,page);
		List<Theme> list = pb.getList();
		return pb;
	}
	/**
	 * 话题广场
	 * @param page
	 * @return 
	 */
	public PageBean ThemeSquareList(int page){
		String hql="from Theme";
		PageBean pb = new PageBean();
		pb = this.queryForPage(hql,null,5,page);
		List<Theme> list = pb.getList();
		return pb;
	}
	/**
	 * 话题广场搜素
	 * @param page
	 * @param topic
	 * @return
	 */
	public PageBean ThemeSquareList(int page,String topic){
		String hql="from Theme where theme like ?";
		List v=new ArrayList();
		v.add("%"+topic+"%");
		PageBean pb = new PageBean();
		pb = this.queryForPage(hql,v,5,page);
		List<Theme> list = pb.getList();
		return pb;
	}
	

}
