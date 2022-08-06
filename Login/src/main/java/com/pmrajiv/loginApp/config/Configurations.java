package com.pmrajiv.loginApp.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import com.pmrajiv.loginApp.repository.UserRepository;
import com.pmrajiv.loginApp.serviceImpl.UserAccessServiceImpl;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class Configurations extends WebSecurityConfigurerAdapter{

	@Resource
	private Environment environment;
	@Autowired
	private UserAccessServiceImpl userDetailsService;
	@Autowired
	UserRepository userRepo;
	
//	@Bean(name = "mappingDataSource")
//	@Qualifier("mappingDataSource")
//	public DataSource mappingDataSource() {
//		String trimmedEachEnvironment = "map";
//
//		BoneCPConfig bonecpConfig = new BoneCPConfig();
//		bonecpConfig.setJdbcUrl(environment.getRequiredProperty(trimmedEachEnvironment + ".dbUrl"));
//		bonecpConfig.setUsername(environment.getRequiredProperty(trimmedEachEnvironment + ".dbUser"));
//		bonecpConfig.setPassword(environment.getRequiredProperty(trimmedEachEnvironment + ".dbPassword"));
//		BoneCPDataSource dataSource = new BoneCPDataSource(bonecpConfig);
//		dataSource.setDriverClass(environment.getRequiredProperty(trimmedEachEnvironment + ".driverClassName"));
//
//		return dataSource;
//	}

	@Bean 
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder(); 
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{ 
		//authenticate and authorize user
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); 
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http.csrf().disable();
 
        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/authenticate") // Submit URL
                .loginPage("/")//
                .defaultSuccessUrl("/dashboard")//
                .failureUrl("/?error=true")//
                .usernameParameter("email")//
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");// Config for Logout Page
    }
}
