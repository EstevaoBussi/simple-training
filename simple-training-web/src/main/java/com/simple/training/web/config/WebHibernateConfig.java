package com.simple.training.web.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"com.simple.training.domain.treino.exercicio",
		"com.simple.training.domain.treino",
		"com.simple.training.domain.nutri.cardapio",
		"com.simple.training.domain.treino.realizado",
		"com.simple.training.domain.usuario"})
@ComponentScan({ "com.simple.training.web.api.treino",
	"com.simple.training.web.api.usuario",
	"com.simple.training.web.api.nutri",
	"com.simple.training.domain.treino.service",
	"com.simple.training.domain.nutri.cardapio.service",
	"com.simple.training.domain.treino.realizado.service",
	"com.simple.training.domain.treino.exercicio.service",
	"com.simple.training.domain.usuario.service"})
public class WebHibernateConfig {
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    	LocalContainerEntityManagerFactoryBean sessionFactory = new LocalContainerEntityManagerFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.simple.training.domain"});
        sessionFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        sessionFactory.setJpaProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("");

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/simple-training");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("show_sql", "true");
        return properties;
    }
    

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
       JpaTransactionManager txManager = new JpaTransactionManager();
       txManager.setEntityManagerFactory(emf);
       return txManager;
    }

}
