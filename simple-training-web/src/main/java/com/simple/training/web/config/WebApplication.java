package com.simple.training.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.simple.training.domain.treino.Treino;
import com.simple.training.domain.treino.exercicio.Exercicio;
import com.simple.training.domain.usuario.Usuario;

@SpringBootApplication
@EnableAutoConfiguration
@Import(value=WebHibernateConfiguration.class)
@Configuration
public class WebApplication extends WebMvcConfigurerAdapter {
	
	@Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            	config.exposeIdsFor(Treino.class);
            	config.exposeIdsFor(Exercicio.class);
            	config.exposeIdsFor(Usuario.class);
            }
        };
    }
	
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate4Module());
        return mapper;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
