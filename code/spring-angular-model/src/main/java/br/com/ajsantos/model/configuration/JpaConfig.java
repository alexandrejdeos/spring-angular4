package br.com.ajsantos.model.configuration;

import java.util.Collections;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * @author ajsantos
 * @date 2017-06-05
 * @description Configuração JPA
 */

@Configuration
@PropertySource("classpath:config/data/hibernate.properties")
public class JpaConfig {
	private static final String PACKAGES_TO_SCAN = "br.com.ajsantos.model.entity";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL = "hibernate.hbm2ddl_auto";
	private static final String PROPERTY_NAME_HIBERNATE_GENERATESTATISTICS = "hibernate.generate_statistics";
	private static final String PROPERTY_NAME_HIBERNATE_CACHE_PROVIDERCLASS = "hibernate.cache.provider_class";
	private static final String PROPERTY_NAME_HIBERNATE_CACHE_USESECONDLEVELCACHE = "hibernate.cache.use_second_level_cache";
	private static final String PROPERTY_NAME_HIBERNATE_CACHE_USEQUERYCACHE = "hibernate.cache.use_query_cache";
	private static final String PROPERTY_NAME_HIBERNATE_CACHE_REGION_FACTORYCLASS = "hibernate.cache.region.factory_class";
	private static final String PROPERTY_NAME_HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS = "hibernate.enable_lazy_load_no_trans";
	private static final String PROPERTY_NAME_HIBERNATE_ID_NEW_GENERATOR_MAPPINGS = "hibernate.id.new_generator_mappings";
	
	@Autowired
	private Environment env;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	  /**
	   * Configura o entity managem.
	   * 
	   * @return Local container do entity manager
	   */
	  @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	    emf.setDataSource(dataSource);
	    emf.setPackagesToScan(PACKAGES_TO_SCAN);
	    emf.setJpaVendorAdapter(jpaVendorAdapter);
	    emf.setJpaProperties(this.jpaProperties());
	    emf.setJpaPropertyMap(Collections.singletonMap("javax.persistence.validation.mode", "none"));
	    return emf;
	  }

	  /**
	   * Carrega informações de propriedade do hibernate.
	   * 
	   * @return Properties
	   */
	  @Bean
	  public Properties jpaProperties() {
	    Properties props = new Properties();
	    if (jpaVendorAdapter instanceof HibernateJpaVendorAdapter) {
	      HibernateJpaVendorAdapter jpda = (HibernateJpaVendorAdapter) jpaVendorAdapter;
	      props.setProperty(PROPERTY_NAME_HIBERNATE_DIALECT, (String) jpda.getJpaPropertyMap().get(PROPERTY_NAME_HIBERNATE_DIALECT));
	      props.setProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL, (String) jpda.getJpaPropertyMap().get(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
	    }
	    props.setProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
	    props.setProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL));
	    props.setProperty(PROPERTY_NAME_HIBERNATE_GENERATESTATISTICS, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_GENERATESTATISTICS));
	    props.setProperty(PROPERTY_NAME_HIBERNATE_CACHE_PROVIDERCLASS, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CACHE_PROVIDERCLASS));
	    props.setProperty(PROPERTY_NAME_HIBERNATE_CACHE_USEQUERYCACHE, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CACHE_USEQUERYCACHE));
	    props.setProperty(PROPERTY_NAME_HIBERNATE_CACHE_USESECONDLEVELCACHE, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CACHE_USESECONDLEVELCACHE));
	    props.setProperty(PROPERTY_NAME_HIBERNATE_CACHE_REGION_FACTORYCLASS, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CACHE_REGION_FACTORYCLASS));
	    props.setProperty(PROPERTY_NAME_HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS));
	    props.setProperty(PROPERTY_NAME_HIBERNATE_ID_NEW_GENERATOR_MAPPINGS, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_ID_NEW_GENERATOR_MAPPINGS));
	    return props;
	  }

	  /**
	   * Configura controle de transação.
	   * 
	   * @return Gerenciador de transação.
	   */
	  @Bean
	  public JpaTransactionManager transactionManager() {
	    JpaTransactionManager txnMgr = new JpaTransactionManager();
	    txnMgr.setEntityManagerFactory(entityManagerFactory().getObject());
	    return txnMgr;
	  }

	  @Bean
	  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	    return new PersistenceExceptionTranslationPostProcessor();
	  }

}
