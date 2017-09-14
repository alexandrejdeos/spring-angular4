package br.com.ajsantos.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.ajsantos.model.configuration.JndiDataSourceConfig;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityAdapter extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JndiDataSourceConfig jndiDataSourceConfig;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		
		auth.jdbcAuthentication().dataSource(this.jndiDataSourceConfig.dataSource()).passwordEncoder(new Md5PasswordEncoder())
			.authoritiesByUsernameQuery("select username,password, enabled from users where username=?")
			.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.
			authorizeRequests()
				.antMatchers(HttpMethod.POST, "/service/**").permitAll()
				.antMatchers(HttpMethod.GET, "/service/**").permitAll()
				.antMatchers("/resources/**", "/imagens/**").permitAll()
				.antMatchers("/angular/**").authenticated();
				
		super.configure(http);
	   
/*		http
	    	.formLogin()
	    		.loginPage("/login/login.xhtml").permitAll();*/
	    			
	    http
	    	.logout()
	    		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	    			.clearAuthentication(true)
	    				.logoutSuccessUrl("/")
	    					.deleteCookies("JSESSIONID")
	    						.invalidateHttpSession(true);
	    http.csrf();
	}

}
