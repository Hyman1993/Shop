package shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类
 * 
 */
public class MailUtils {
	/**
	 * 发送邮件的方法
	 * @param to	:收件人
	 * @param code	:激活码
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void sendMail(String to,String code) throws AddressException, MessagingException{
		/**
		 * 1.获得一个Session对象.
		 * 2.创建一个代表邮件的对象Message.
		 * 3.发送邮件Transport
		 */
		// 1.获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.163.com");
		  props.setProperty("mail.smtp.auth","true");
		  Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("15295125311","peng5701582");
			}
			
		};
		Session session = Session.getInstance(props,auth);
		// 2.创建邮件对象:
		Message message = new MimeMessage(session);
		// 设置发件人:
		
			message.setFrom(new InternetAddress("15295125311@163.com"));
			// 设置收件人:
			message.addRecipient(RecipientType.TO,new InternetAddress(to));
			// 抄送 CC   密送BCC
			// 设置标题
			message.setSubject("来自购物商城官方激活邮件");
			// 设置邮件正文:
			message.setContent("<h1>购物商城官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://127.0.0.1/shop1.0/user_active.action?code="+code+"'>http://127.0.0.1:8080/shop1.0/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			// 3.发送邮件:
			Transport.send(message);
	

	
	}
}
