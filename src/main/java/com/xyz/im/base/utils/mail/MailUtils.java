package com.xyz.im.base.utils.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
public class MailUtils {

	public static boolean sendMail(String[] receivers, MailConfig config, MailConfigAdditional config2) {
		boolean result = false;
		Transport transport = null;
		try {
			Properties props = new Properties();

			// 开启debug调试
			// props.setProperty("mail.debug", "true");
			// 发送服务器需要身份验证
			props.setProperty("mail.smtp.auth", "true");
			// 设置邮件服务器主机名
			props.setProperty("mail.host", config.getHost());
			// 发送邮件协议名称
			props.setProperty("mail.transport.protocol", config.getProtocol());

			// 开启ssl加密，目前qq是要开的
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.socketFactory", sf);

			Session session = Session.getInstance(props);

			// 定义邮件主题,内容,发件人
			Message msg = new MimeMessage(session);
			msg.setSubject(config2.getSubject());
			StringBuilder builder = new StringBuilder();
			builder.append(config2.getContent());
			// msg.setText(builder.toString());
			msg.setContent(builder.toString(), "text/html;charset=utf-8");
			msg.setFrom(new InternetAddress(config.getUsername(), config2.getNickname(), config.getEncoding()));

			// 传输连接，并发送
			transport = session.getTransport();
			transport.connect(config.getHost(), config.getUsername(), config.getPassword());
			Address[] address = new Address[receivers.length];
			for (int i = 0; i < receivers.length; i++) {
				address[i] = new InternetAddress(receivers[i]);
			}
			transport.sendMessage(msg, address);
			result = true;
		} catch (Exception e) {
			result = false;
			log.error("邮件发送失败", e);
		} finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				log.error("连接关闭失败", e);
			}
		}
		return result;
	}
}
