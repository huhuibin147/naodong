package com.nd.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nd.dao.IdentifyDao;
import com.nd.entity.Identify;

@Component
public class MailUtil {
	private static final String EMAIL = "ndnaodong@126.com";//ndnaodong@126.com
	private static final String NICKNAME = "@脑洞";
	private static final String PASSWORD = "naodong666";
	private static final String HOST = "smtp.126.com";
	
	@Autowired
	private IdentifyDao identifyDao;
	
	/**
	 * 发送验证邮件
	 * @param emailAddress	邮箱地址
	 * @throws Exception
	 */
	public void sendMail(String emailAddress) throws Exception{
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", HOST);
		props.setProperty("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		String identifyCode = this.generateIdentifyCode();
		
		Identify identify = new Identify();
		identify.setEmail(emailAddress);
		identify.setCode(identifyCode);
		identifyDao.saveOrUpdate(identify);
		try{
			String content = "亲爱的@脑洞用户，您好！<br><br> "
					+"您的验证码是："+identifyCode+"<br>"
					+"此验证码将用于验证身份，修改密码密保等。请勿将验证码透露给其他人。 <br><br>"
					+"本邮件由系统自动发送，请勿直接回复！<br>"
					+"感谢您的访问，祝您使用愉快";
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(EMAIL,NICKNAME,"UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailAddress,"@脑洞用户","UTF-8"));
			message.setSubject("@脑洞账号注册验证","UTF-8");
			message.setContent(content,"text/html;charset=UTF-8");
			message.setSentDate(new Date());
			message.saveChanges();
			
			Transport transport = session.getTransport();
			transport.connect(EMAIL, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println(identify.toString());

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成6位验证码
	 * @return
	 */
	private String generateIdentifyCode() {
		StringBuffer sb = new StringBuffer();
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9' };

		int count = 0;
		while (count < 6) {
			int randomNum = ((int) (Math.random() * 1000)) % 62;
			sb.append(str[randomNum]);
			count++;
		}
		return sb.toString();
	}
	
//	public static void main(String[] args) throws Exception {
//		Properties props = new Properties();
//		props.setProperty("mail.transport.protocol", "smtp");
//		props.setProperty("mail.host", HOST);
//		props.setProperty("mail.smtp.auth", "true");
//		
//		Session session = Session.getDefaultInstance(props);
//		session.setDebug(true);
//		
//		MimeMessage message = new MimeMessage(session);
//		
//		message.setFrom(new InternetAddress(EMAIL,NICKNAME,"UTF-8"));
//		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("1461072766@qq.com","aaa","UTF-8"));
//		message.setSubject("邮件测试","UTF-8");
//		message.setContent("hbsb","text/html;charset=UTF-8");
//		message.setSentDate(new Date());
//		message.saveChanges();
//		
//		Transport transport = session.getTransport();
//		transport.connect(EMAIL, PASSWORD);
//		for(int i=0;i<5;i++){
//		transport.sendMessage(message, message.getAllRecipients());
//		}
//		transport.close();
//	}
}
