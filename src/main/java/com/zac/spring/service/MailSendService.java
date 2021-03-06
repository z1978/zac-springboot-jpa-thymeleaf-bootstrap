package com.zac.spring.service;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @类名: MailSender<br>
 * @描述: 邮件发送<br>
 */
@Service
public class MailSendService {

	@Autowired
	JavaMailSender mailSender;

	// 发件人名称设置
	@Value("${spring.mail.self.username}")
	private String sendUser;

	public void sendEmail(String toMail, String title, String content) throws MessagingException {
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		message.setFrom(sendUser);
		message.setTo(toMail);
		message.setSubject(title);
		message.setText(content);

		mailSender.send(mimeMessage);
	}

	/**
	 * @方法名: sendSimpleMail<br>
	 * @描述: 发送普通文本格式的邮件<br>
	 * @param toMail
	 *            收件人，多个用英文格式逗号分隔
	 * @param replyTo
	 *            抄送人，多个用英文格式逗号分隔
	 * @param title
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @param flies
	 *            附件
	 * @throws Exception
	 */
	public void sendSimpleMail(String toMail, String replyTo, String title, String content, List<File> files)
			throws Exception {
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(sendUser);
		helper.setSubject(title);
		helper.setText(content);

		// 设置多个收件人
		String[] toAddress = toMail.split(",");
		helper.setTo(toAddress);

		// 设置多个抄送
		if (StringUtils.hasText(replyTo)) {
			// helper.setReplyTo(replyTo);
			InternetAddress[] internetAddressCC = InternetAddress.parse(replyTo);
			mimeMessage.setReplyTo(internetAddressCC);
		}

		// 添加附件
		if (null != files) {
			for (File file : files) {
				helper.addAttachment(file.getName(), file);
			}
		}

		mailSender.send(mimeMessage);
	}

	/**
	 * @方法名: sendHtmlMail<br>
	 * @描述: 发送HTML格式的邮件<br>
	 * @param toMail
	 *            收件人，多个用英文格式逗号分隔
	 * @param replyTo
	 *            抄送人，多个用英文格式逗号分隔
	 * @param title
	 *            邮件主题
	 * @param htmlContent
	 *            邮件内容
	 * @param files
	 *            附件
	 * @throws Exception
	 */
	public void sendHtmlMail(String toMail, String replyTo, String title, String htmlContent, List<File> files)
			throws Exception {
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(sendUser);
		helper.setSubject(title);
		helper.setText(htmlContent, true);

		// 设置多个收件人
		String[] toAddress = toMail.split(",");
		helper.setTo(toAddress);

		// 设置多个抄送
		if (StringUtils.hasText(replyTo)) {
			// helper.setReplyTo(replyTo);
			InternetAddress[] internetAddressCC = InternetAddress.parse(replyTo);
			mimeMessage.setRecipients(RecipientType.CC, internetAddressCC);
		}

		// 添加附件
		if (null != files) {
			for (File file : files) {
				helper.addAttachment(file.getName(), file);
			}
		}

		mailSender.send(mimeMessage);
	}

}
