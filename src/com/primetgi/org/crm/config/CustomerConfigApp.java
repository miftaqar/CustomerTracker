package com.primetgi.org.crm.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.primetgi.org.crm.dao.CustomerDAO;
import com.primetgi.org.crm.dao.CustomerDAOImpl;
import com.primetgi.org.crm.service.CustomerService;
import com.primetgi.org.crm.service.CustomerServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.primetgi.org.crm.controller")
@EnableTransactionManagement
public class CustomerConfigApp implements WebMvcConfigurer {

	/*
	@Value("${db.driverClass}")
	private String dbDriver;
	
	@Value("${db.JdbcUrl}")
	private String dbURL;
	
	@Value("${db.user}")
	private String dbUser;
	
	@Value("${db.password}")
	private String dbPassword;
	*/
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	/*
	 * Configuring Hibernate Connection Pool
	 */

	// Step 1: Define Database Datasource / connection pool

	@Bean
	public ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setJdbcUrl("jdbc:sqlserver://user-229;databaseName=user229DB");
		dataSource.setUser("sa");
		dataSource.setPassword("admin");
		dataSource.setMinPoolSize(5);
		dataSource.setMaxPoolSize(20);
		dataSource.setMaxIdleTime(30000);
		return dataSource;
	}

	// Step 2: Set up Hibernate Session Factory
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(comboPooledDataSource());
		sessionFactoryBean.setPackagesToScan(new String[] { "com.primetgi.org.crm.entity" });
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		return sessionFactoryBean;

	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}

	// Step 3: Set Up Hibernate Transactional Manager

	public HibernateTransactionManager hibernateTransactionManager() throws PropertyVetoException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory((SessionFactory) sessionFactory());
		return transactionManager;
	}

	@Bean
	public PlatformTransactionManager txManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		return jpaTransactionManager;
	}

	// Add support for reading web resources: css, images, js etc
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	// load the custom message resources
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasenames("resources/customerTracker");
		resourceBundleMessageSource.setDefaultEncoding("UTF-8");
		resourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
		return resourceBundleMessageSource;

	}

	/*
	 * Bean Classes
	 */
	@Bean
	public CustomerDAO customerDAO() {
		return new CustomerDAOImpl();
	}

	@Bean
	public CustomerService customerService() {
		return new CustomerServiceImpl();
	}

}
