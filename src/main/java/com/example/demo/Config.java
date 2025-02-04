package com.example.demo;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.models.Service;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class Config {

	private Properties getHibernatePropertiesSessionFactory() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		hibernateProperties.put("show_sql", "true");
		hibernateProperties.put("current_session_context_class", "thread");
		//hibernateProperties.put("hbm2ddl.auto", "create");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.put("hibernate.dbcp.initialSize", "5");
		hibernateProperties.put("hibernate.dbcp.maxTotal", "20");
		hibernateProperties.put("hibernate.dbcp.maxIdle", "10");
		hibernateProperties.put("hibernate.dbcp.minIdle", "5");
		hibernateProperties.put("hibernate.dbcp.maxWaitMillis", "-1");
		return hibernateProperties;
	}

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setHibernateProperties(getHibernatePropertiesSessionFactory());
		localSessionFactoryBean.setDataSource(getDataSource());
		localSessionFactoryBean.setAnnotatedClasses(new Class[] { Service.class });
		return localSessionFactoryBean;
	}
/**
	@Bean(name = "dataSource")
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/gcpcalculator");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");
		return driverManagerDataSource;
	}
	
*/	
	  @Bean(name = "jdbcTemplate")
	  public JdbcTemplate getJdbcTemplate() {
	    JdbcTemplate jdbcTemplate = new JdbcTemplate();
	    jdbcTemplate.setDataSource(getDataSource());
	    return jdbcTemplate;
	  }

//https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/cloud-sql/mysql/servlet
//https://github.com/GoogleCloudPlatform/java-docs-samples/blob/master/cloud-sql/mysql/servlet/src/main/java/com/example/cloudsql/ConnectionPoolContextListener.java
//34.70.241.89 public IP
//10.50.160.3 private IP
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		try {
			// this is for GKE connectivity using private IP set from no_proxy_private_ip.yaml dbsecret1. follow https://cloud.google.com/sql/docs/mysql/connect-kubernetes-engine
			// this can be for Cloud run connectivity as well using private IP set from cloud run console environment variables. follow https://cloud.google.com/sql/docs/mysql/connect-run
			String DB_HOST = System.getenv("DB_HOST"); //10.50.160.3
			
			if (StringUtils.isBlank(DB_HOST)) { // this is for compute engine connectivity using public IP
				DB_HOST = "34.70.241.89";
			    config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
			    config.addDataSourceProperty("cloudSqlInstance", "gebu-demo-projects:us-central1:gcpcalculator"); // INSTANCE_CONNECTION_NAME
			}
			String baseUrl = "jdbc:mysql://";
			String finalURL = baseUrl + DB_HOST + ":3306" + "/gcpcalculator";
			System.err.println("Final URL----------------------->" + finalURL);
			config.setJdbcUrl(finalURL);

		} catch (Exception e) {
			System.err.println("Final URL exception-------------->");
		}

		config.setUsername("root"); 
		config.setPassword("root"); 

		config.setMaximumPoolSize(5);
		config.setConnectionTimeout(10000); // 10 seconds
		config.setIdleTimeout(600000); // 10 minutes
		config.setMaxLifetime(1800000); // 30 minutes
		DataSource pool = new HikariDataSource(config);
		return pool;
	}


	/**
	 @Bean public ViewResolver getViewResolver(){ 
	 	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/pages/"); 
	    resolver.setSuffix(".jsp");
	    resolver.setViewClass(JstlView.class); 
	    return resolver; 
	}
	*/

}
