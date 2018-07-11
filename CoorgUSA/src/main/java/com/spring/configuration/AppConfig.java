package com.spring.configuration;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spring")
public class AppConfig extends WebMvcConfigurerAdapter {

	// ViewControllerRegistry to deliver application views
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/aboutcoorg").setViewName("aboutcoorg");
		registry.addViewController("/aboutcoorgusa").setViewName("aboutcoorgusa");
		registry.addViewController("/contactus").setViewName("contactus");
		registry.addViewController("/aboutus").setViewName("aboutus");
		registry.addViewController("/privacypolicy").setViewName("privacypolicy");
		registry.addViewController("/termsofservice").setViewName("termsofservice");
		registry.addViewController("/thankyou").setViewName("thankyou");
		registry.addViewController("/accessDenied").setViewName("accessDenied");
	}

	// ViewResolvers to deliver preferred views
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	// Handle exceptions to return a single view
	@Bean
	public SimpleMappingExceptionResolver exceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

		Properties exceptionMappings = new Properties();

		exceptionMappings.put("java.lang.Exception", "/error");
		exceptionMappings.put("java.lang.RuntimeException", "/error");

		exceptionResolver.setExceptionMappings(exceptionMappings);

		Properties statusCodes = new Properties();
		statusCodes.put("error/404", "404");
		statusCodes.put("error/error", "500");
		exceptionResolver.setStatusCodes(statusCodes);

		return exceptionResolver;
	}

	// ResourceHandlers to serve resources like CSS/ Javascript
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	// Lookup any validation/error message in property files
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	// File upload and download
	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver resolver() {
		return new StandardServletMultipartResolver();
	}

	// Email
	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		// Using gmail
		String host = "smtp.gmail.com";

		mailSender.setHost(host);
		mailSender.setPort(465);// port number 465,587
		mailSender.setUsername("kushalappapp@gmail.com");
		mailSender.setPassword("Gmail@86");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.debug", "true");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	// FreeMarker configuration
	@Bean
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("/fmtemplates/");
		return bean;
	}

	// Required when handling '.' in @PathVariables which otherwise ignore
	// everything after last '.' in @PathVaidables argument. e.g. @gmail.com is
	// sent as @gmail
	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}
}