package com.zac.spring.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Created by Zac
 */
@Configuration
public class AppConfig {
	
	/**
	 * 会话区域解析器;
	 * @return
	 */	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		// 设置默认区域
		slr.setDefaultLocale(Locale.CHINA);
//		slr.setDefaultLocale(Locale.getDefault());
		return slr;
	}
	
	/**
	 * cookie区域解析器;
	 * @return
	 */	
//	@Bean
//    public LocaleResolver localeResolver() {
//       CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        //设置默认区域,
//       cookieLocaleResolver.setDefaultLocale(Locale.CHINA);
//       cookieLocaleResolver.setCookieMaxAge(3600);//设置cookie有效期.
//        return cookieLocaleResolver;
//    }
	
	/**
	 * 固定的区域解析器;
	 * @return
	 */
//	@Bean
//	public LocaleResolver localeResolver() {
//		FixedLocaleResolver fixedLocaleResolver = new FixedLocaleResolver();
//		// 设置默认区域,
//		fixedLocaleResolver.setDefaultLocale(Locale.US);
//		return fixedLocaleResolver;
//	}

// http://412887952-qq-com.iteye.com/blog/2312274
//================================================== 	
	/**
	 * MessageSourceの拡張クラスのBean登録
	 * 
	 * @return
	 */
	// @Bean
	// public MessageSourceImpl messageSourceImpl() {
	// return new MessageSourceImpl();
	// }
//	@Bean
//	public MappedInterceptor interceptor() {
//		return new MappedInterceptor(new String[] { "/**" }, localeChangeInterceptor());
//	}
//
//	/**
//	 * URLの「lang」パラメータで切り替えできるようにする
//	 */
//	@Bean
//	public LocaleChangeInterceptor localeChangeInterceptor() {
//		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//		localeChangeInterceptor.setParamName("lang");
//		return localeChangeInterceptor;
//	}
//
//	@Bean
//	public SessionLocaleResolver localeResolver() {
//		return new SessionLocaleResolver();
//	}
//
//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("i18n/messages");
//		messageSource.setDefaultEncoding("UTF-8");
//		return messageSource;
//	}
}