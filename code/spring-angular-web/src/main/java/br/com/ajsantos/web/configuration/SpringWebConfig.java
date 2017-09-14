package br.com.ajsantos.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author ajsantos
 * @date 2017-06-08
 * @description Classe responsável por fornercer configurações por trás da
 *              configuração do JAVA MVC
 */

@Configuration
public class SpringWebConfig extends WebMvcConfigurationSupport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.config.annotation.
	 * WebMvcConfigurationSupport#addResourceHandlers(org.springframework.web.
	 * servlet.config.annotation. ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.config.annotation.
	 * WebMvcConfigurationSupport#configureDefaultServletHandling(org.
	 * springframework.web.servlet.config.
	 * annotation.DefaultServletHandlerConfigurer)
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.config.annotation.
	 * WebMvcConfigurationSupport#configureViewResolvers(org.springframework.web
	 * .servlet.config.annotation. ViewResolverRegistry)
	 */
	@Override
	protected void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".xhtml");
		registry.viewResolver(viewResolver);
	}

}
