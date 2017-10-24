package com.nd.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nd.entity.User;

@Transactional
@Repository
public class UserDao extends BaseDao<User>{
	
	/**
	 * 登录
	 * @param email
	 * @param psw
	 * @return
	 */
	public String login(String email,String psw){
		String check = checkUser(email);
		if(check != "用户不存在"){
			if(check.equals(psw)){
				return "密码正确";
			}else{
				return "登录邮箱或密码不正确";
			}
		}else{
			return check;
		}
	}
	/**
	 * 判断用户是否存在   存在则返回密码
	 * @param email
	 * @return
	 */
	private String checkUser(String email){
		String hql="from User where email=?";
		Query query = gs().createQuery(hql);
		query.setParameter(0, email);
		List<User> list = query.list();
		if(list.size()==0){
			return "用户不存在";
		}
		else{
			return list.get(0).getPassword();
		}
		
	}
	
	/**
	 * 判断用户是否存在，存在返回true，不存在返回false
	 * @param email
	 * @return
	 */
	public boolean checkUserExist(String email){
		String hql = "from User where email=?";
		SQLQuery query = gs().createSQLQuery("select 1 from User u where u.email = ? "
				+ "union select 1 from Identify i where i.email = ?");
		query.setParameter(0, email);
		query.setParameter(1, email);
		List<User> list = query.list();
		return list.isEmpty();
	}
	
	/**
	 * 检验注册验证码有效性,验证码无效返回true,有效返回false
	 * @param identifyCode
	 * @param email
	 * @return
	 */
	public boolean checkIdentifyCode(String identifyCode,String email){
		String nativeSql = "select 1 from identify icode where icode.email = ? "
						 + 		"and icode.code = ? ";
		SQLQuery query = gs().createSQLQuery(nativeSql);
		query.setParameter(0, email);
		query.setParameter(1, identifyCode);
		List<Object> list = query.list();
		return list.isEmpty();
	}
	
	
	/**
	 * 查出用户id
	 * @param email
	 * @return
	 */
	public Integer getUserId(String email){
		String hql = "from User where email=?";
		List<String> values = new ArrayList<>();
		values.add(email);
		return this.getListByHQL(hql, values).get(0).getId();
		
	}
	/**
	 * 获得用户名字
	 * @param email
	 * @return
	 */
	public String getUsername(String email){
		String hql = "from User where email=?";
		List<String> values = new ArrayList<>();
		values.add(email);
		return this.getListByHQL(hql, values).get(0).getName();
	}
	/**
	 * 获得用户img
	 * @param email
	 * @return
	 */
	public String getimgByEmail(String email){
		String hql = "from User where email=?";
		List<String> values = new ArrayList<>();
		values.add(email);
		return this.getListByHQL(hql, values).get(0).getImg();
	}
	/**
	 * 获得用户img
	 * @param uid
	 * @return
	 */
	public String getImgByUid(int uid){
		String hql = "from User where id=?";
		List<Integer> values = new ArrayList<>();
		values.add(uid);
		return this.getListByHQL(hql, values).get(0).getImg();
	}
	/**
	 * 获得用户对象
	 * @param uid
	 * @return
	 */
	public User getUserByUid(int uid){
		String hql = "from User where id=?";
		List<Integer> values = new ArrayList<>();
		values.add(uid);
		return this.getListByHQL(hql, values).get(0);
	}
	
	/**
	 * 获得用户对象
	 * @param uid
	 * @return
	 */
	public User getUserByUid(String email){
		String hql = "from User where email=?";
		List<String> values = new ArrayList<>();
		values.add(email);
		return this.getListByHQL(hql, values).get(0);
	}
	
	public boolean changePassword(String email,String pwd,String newPwd){
		if(pwd.equals(newPwd)){
			return true;
		}
		String sql = "update user "
				+ "set user.`password` = :newPwd "
				+ "where `user`.email = :email "
				+ "and `user`.`password` = :pwd";
		Query query = gs().createSQLQuery(sql);
		query.setString("newPwd", newPwd);
		query.setString("email", email);
		query.setString("pwd", pwd);
		return query.executeUpdate()>0?true:false;
	}
	
	public boolean updateUserInfo(String email,String name,String sex,String birthday,String interest,String intro){
		String sql = "update user " +
				"set sex = :sex," +
				" name = :name," +
				" birthday = :birthday," +
				" interest = :interest," +
				" introduce = :introduce " +
				"WHERE" +
				"	email = :email";
		Query query = gs().createSQLQuery(sql);
		query.setString("sex", sex);
		query.setString("name", name);
		query.setString("birthday", birthday);
		query.setString("interest", interest);
		query.setString("introduce", intro);
		query.setString("email", email);
		try{
			query.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
