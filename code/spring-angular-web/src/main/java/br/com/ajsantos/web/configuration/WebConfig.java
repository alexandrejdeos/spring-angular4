package br.com.ajsantos.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "br.com.ajsantos.web.bean.page"})
public class WebConfig{

}
