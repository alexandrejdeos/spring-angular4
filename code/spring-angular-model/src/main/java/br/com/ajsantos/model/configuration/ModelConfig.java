package br.com.ajsantos.model.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 
 * @author ajsantos
 * @date 2017-06-05
 * @description Configuração de mapeamento spring do modelo do projeto.
 */
@Configuration
@Import( {JndiDataSourceConfig.class, JpaConfig.class, RepositoryConfig.class} )
public class ModelConfig {

}
