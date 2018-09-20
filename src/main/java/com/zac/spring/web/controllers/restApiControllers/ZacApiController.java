package com.zac.spring.web.controllers.restApiControllers;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zac.spring.service.MailSendService;

/**
 * Created by Zac
 */

@ComponentScan
@Controller
@RequestMapping("")
public class ZacApiController {

	final static Logger logger = LoggerFactory.getLogger(ZacApiController.class);

	private static final String TM_INDEX = "TitanMaster/index";

	@Autowired
	private MailSendService mailSendService;

	public ZacApiController(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}

	@RequestMapping(value = { "/tm/mail" }, method = { RequestMethod.GET })
	public ModelAndView mail(ModelAndView mav) {
		logger.debug("TM + mail");
		try {
			mailSendService.sendEmail("znbvpn@yahoo.co.jp", "String title", "String content");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("model", "model");
		mav.setViewName(TM_INDEX);
		return mav;
	}

}
