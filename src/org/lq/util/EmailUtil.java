package org.lq.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.lq.exception.NoSuchEmailUrlException;

public class EmailUtil {
	/*
	 *  HtmlEmail emailObject = new HtmlEmail();
		  emailObject.setHostName("smtp.163.com");
		  emailObject.addTo(email);
		  emailObject.setFrom("xiaozai_05@163.com");
		  emailObject.setSubject("网上书城激活邮件");
		  emailObject.setAuthentication("xiaozai_05", "666666x");
		  // set the html message
		  emailObject.setHtmlMsg(emailContent);

		  // set the alternative message
		  emailObject.setTextMsg("Your email client does not support HTML messages");
		  // send the email
		  emailObject.send();*/
	public static void send(String emailContent,String email) throws EmailException {
		 HtmlEmail emailObject = new HtmlEmail();
		  emailObject.setHostName("smtp.163.com");
		  emailObject.addTo(email);
		  emailObject.setFrom("xiaozai_05@163.com");
		  emailObject.setSubject("网上书城激活邮件");
		  emailObject.setAuthentication("xiaozai_05", "666666x");
		  // set the html message
		  emailObject.setHtmlMsg(emailContent);

		  // set the alternative message
		  emailObject.setTextMsg("Your email client does not support HTML messages");
		  // send the email
		  emailObject.send();
	}
	public static String getEmailPage(String email) throws NoSuchEmailUrlException {
		if(email.contains("@163.")) {
			return "https://mail.163.com";
		}else if(email.contains("@qq.")) {
			return "https://mail.qq.com";
		}else {
			throw new NoSuchEmailUrlException("the invalid email url :"+email);
		}
	}
}
