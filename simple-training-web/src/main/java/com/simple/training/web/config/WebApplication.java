package com.simple.training.web.config;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@SpringBootApplication
@Import(value=WebHibernateConfig.class)
@Configuration
public class WebApplication {
	
	@Bean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CORSFilter corsFilter = new CORSFilter();
        registration.setFilter(corsFilter);
        registration.addUrlPatterns("/api/*");
        return registration;
    }
	
	@Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {
            @Override
        	public void configureHttpMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
            	messageConverters.add(new MappingJackson2HttpMessageConverter());
         
                super.configureHttpMessageConverters(messageConverters);
            }
        };
        
    }
	
	public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
