package com.zac.spring.web.controllers.viewControllers;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.zac.spring.service.utility.LocaleMessageSourceService;
import com.zac.spring.web.dto.UserDto;

/**
 * Created by Zac
 */

@Controller
@RequestMapping("")
public class IndexController {
	@Resource
	private LocaleMessageSourceService localeMessageSourceService;
	
	@Autowired
	MessageSource messageSource;
//	@Autowired
//	MessageSourceImpl messageSourceImpl;

	@GetMapping(value = { "/", "/index" })
	public String index(ModelMap model) {
		Locale locale = LocaleContextHolder.getLocale();
		String msg = messageSource.getMessage("welcome", null, locale);
		System.out.println(msg);
		String msg3 = localeMessageSourceService.getMessage("welcome");
		System.out.println(msg3);

		String messageSIMPLIFIED_CHINESE = messageSource.getMessage("hoge", new String[] { "Zac," }, Locale.SIMPLIFIED_CHINESE);
		System.out.println(messageSIMPLIFIED_CHINESE);
		String messageJAPAN = messageSource.getMessage("hoge", new String[] { "Zac," }, Locale.JAPAN);
		System.out.println(messageJAPAN);
		String messageJAPANESE = messageSource.getMessage("hoge", new String[] { "Zac," }, Locale.JAPANESE);
		System.out.println(messageJAPANESE);
		String messageENGLISH = messageSource.getMessage("hoge", new String[] { "Zac," }, Locale.ENGLISH);
		System.out.println(messageENGLISH);
		String messageGetDefault = messageSource.getMessage("content.title", null, Locale.getDefault());
		System.out.println(messageGetDefault);
//		String messageENGLISH2 = messageSourceImpl.getMessage("content.title", null, Locale.ENGLISH);
//		System.out.println(messageENGLISH2);

		model.addAttribute("titleModel", "Test Title");
		
		return "website/404";
//		return "website/index";
	}

	@GetMapping(value = "/login")
	public String login() {
		return "website/login";
	}

	@GetMapping(value = "/register")
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "website/register";
	}
	
	@RequestMapping("/changeSessionLanauage")
	public String changeSessionLanauage(HttpServletRequest request, HttpServletResponse response, String lang) {
//		System.out.println(lang);
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if ("ja".equals(lang)) {
			localeResolver.setLocale(request, response, new Locale("ja", "JP"));
		} else if ("en".equals(lang)) {
			localeResolver.setLocale(request, response, new Locale("en", "US"));
		} else {
			localeResolver.setLocale(request, response, new Locale("zh", "CN"));
		}
		return "redirect:/index";
	}
}
