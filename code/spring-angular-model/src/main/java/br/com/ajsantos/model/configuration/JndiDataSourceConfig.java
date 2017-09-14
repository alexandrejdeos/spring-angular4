package br.com.ajsantos.model.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * @author ajsantos
 * @date 2017-06-05
 * @description Configuração de data source via jndi.
 */
@Configuration
@PropertySource("classpath:config/data/hibernate.properties")
public class JndiDataSourceConfig {

	private static final String DATA_SOURCE = "java:jboss/datasources/mysqlDS";

	@Autowired
	private Environment env;

	/**
	 * Recupera o datasource via jndi.
	 * 
	 * @return Instancia do datasource.
	 */
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		JndiDataSourceLookup lookup = new JndiDataSourceLookup();
		lookup.setResourceRef(true);
		return lookup.getDataSource(DATA_SOURCE);
	}

	/**
	 * Configura o Jpa Vendo.
	 * 
	 * @return Instancia do Jpa Vendor
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(env.getProperty("hibernate.show_sql", Boolean.class));
		hibernateJpaVendorAdapter.setGenerateDdl(env.getProperty("hibernate.generate_dll", Boolean.class));
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}
}
