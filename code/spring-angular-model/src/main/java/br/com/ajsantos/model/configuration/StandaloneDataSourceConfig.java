package br.com.ajsantos.model.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * @author ajsantos
 * @date 2017-06-05
 * @description Configuração de datasource, embedded.
 */
@Configuration
@Profile("test")
public class StandaloneDataSourceConfig {

	/**
	 * Configura um datasource em memoria.
	 * 
	 * @return Datasource instanciado.
	 */
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder embedded = new EmbeddedDatabaseBuilder();
		embedded.setType(EmbeddedDatabaseType.HSQL);
		return embedded.build();
	}

	/**
	 * Configura o Jpa Vendo.
	 * 
	 * @return Instancia do Jpa Vendor
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.HSQL);
		return hibernateJpaVendorAdapter;
	}
}
