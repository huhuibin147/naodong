package com.nd.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nd.dao.AnswerDao;
import com.nd.dao.ArticleDao;
import com.nd.dao.ConcernQuestionDao;
import com.nd.dao.QuestionDao;
import com.nd.dao.ThemeDao;
import com.nd.dao.UserDao;
import com.nd.entity.Question;
import com.nd.entity.Theme;
import com.nd.entity.User;
import com.nd.utils.PageBean;



/*
 * 测试DAO层
 * 使用Springtest测试套件测试 
 * 
 *引入 spring-test-4.3.2.RELEASE junit-4.12
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring.xml","classpath*:/springmvc.xml"})
public class test1 {
  
	 
	/*
	 * 声明所有DAO类
	 */
	@Autowired
	private UserDao userDao;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private ThemeDao themeDao;
	@Autowired
	private AnswerDao answerDao;
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ConcernQuestionDao concernQuestionDao;
	
	
	
	@Test
	public void t1(){
		
//		User u = userDao.get(1);
//		System.out.println(u.toString());
		List<User> list = userDao.getAll();
		for (User user : list) {
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void t2(){
		userDao.login("a", "ad");
	}
	
	@Test
	public void t3(){
		List<Question> list = questionDao.getAll();
		for (Question q : list) {
			System.out.println(q.toString());
		}
	}
	@Test
	public void t4(){
		Question q = new Question();
		for(int i=0;i<50;i++){
			q.setContent(i+"");
			questionDao.add(q);
		}
	}
 
	@Test
	public void t5(){
		String hql="from Question";
		PageBean pb = new PageBean();
		pb = questionDao.queryForPage(hql,null,5,2);
		List<Question> list = pb.getList();
		System.out.println("总页数:"+pb.getTotalPage());
		for (Question question : list) {
			System.out.println(question.toString());
		}
	}

	@Test
	public void t6(){
		PageBean pb = new PageBean();
		pb = questionDao.QuestionList(2);
		List list = pb.getList();
		System.out.println(list.toString());
	}
	@Test
	public void t7(){
		int a =themeDao.add(new Theme("aaa"));
		System.out.println(a);
	}
	@Test
	public void t8(){
		int i =concernQuestionDao.exitConcerQues(3, 1);
		System.out.println(i);
	}
}
