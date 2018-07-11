package com.spring.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.spring.model.User;

import freemarker.template.Configuration;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	Configuration freemarkerConfiguration;

	@Override
	public void sendRegistrationEmail(Object object) {
		User user = (User) object;
		MimeMessagePreparator preparator = getRegistrationMessagePreparator(user);

		try {
			mailSender.send(preparator);
			System.out.println("Message has been sent.............................");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private MimeMessagePreparator getRegistrationMessagePreparator(final User user) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setSubject("Phonenix-Coorg Website Registration!");
				helper.setFrom("kushalappapp@gmail.com");
				helper.setTo(user.getEmail());

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user);

				String text = getFMRegistrationTemplateContent(model);// UseFreemarker
				System.out.println("Template content : " + text);

				// use the true flag to indicate you need a multipart message
				helper.setText(text, true);
			}
		};
		return preparator;
	}

	public String getFMRegistrationTemplateContent(Map<String, Object> model) {
		StringBuffer content = new StringBuffer();
		try {
			content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
					freemarkerConfiguration.getTemplate("fm_registrationmailTemplate.txt"), model));
			return content.toString();
		} catch (Exception e) {
			System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
		}
		return "";
	}
}
