package it.uniroma3.siw.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static it.uniroma3.siw.model.Credentials.ADMIN_ROLE;

import javax.sql.DataSource;

/**
 * The AuthConfiguration is a Spring Security Configuration.
 * It extends WebSecurityConfigurerAdapter, meaning that it provides the settings for Web security.
 */
@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * The datasource is automatically injected into the AuthConfiguration (using its getters and setters)
     * and it is used to access the DB to get the Credentials to perform authentication and authorization
     */
    @Autowired
    DataSource datasource;

    /**
     * This method provides the whole authentication and authorization configuration to use.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		// authorization paragraph: here we define WHO can access WHICH pages
                .authorizeRequests()
                // anyone (authenticated or not) can access the welcome page, the login page,
				// and the registration page
                .antMatchers(HttpMethod.GET, "/", "/index", "/login", "/chisiamo", "/contattaci", "/home", "/users/register", "/css/**", "/images/**", "/layout/**").permitAll()
                // anyone (authenticated or not) can send POST requests to the login endpoint
				// and the register endpoint 
                .antMatchers(HttpMethod.POST, "/login", "/users/register", "/chisiamo", "/contattaci").permitAll()
                // only authenticated users with ADMIN authority can access the admin page
                .antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
                // all authenticated users can access all the remaining other pages
                .anyRequest().authenticated()

                // login paragraph: here we define how to login
				// use formlogin protocol to perform login
                .and().formLogin()
                // after login is successful, redirect to the logged user homepage
               // .loginPage("/login")
                // se il login ha successo, si viene rediretti al path /default
               //.defaultSuccessUrl("/default")
                .defaultSuccessUrl("/index")
                
                
                // logout paragraph: we are going to define here how to logout
				.and().logout().logoutUrl("/logout") // logout is performed when sending a GET to "/logout"
				.logoutSuccessUrl("/index") // after logout is successful, redirect to /index page
				.invalidateHttpSession(true) // interrompo la sessione quando faccio il logout
				.clearAuthentication(true).permitAll();
        
                /*
                // logout paragraph: qui definiamo il logout
                .and().logout()
                // il logout Ã¨ attivato con una richiesta GET a "/logout"
                .logoutUrl("/logout")
                // in caso di successo, si viene reindirizzati alla /index page
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index")        
                .invalidateHttpSession(true)
                .clearAuthentication(true).permitAll();
                */
    }

    /**
	 * This method provides the SQL queries to get username and password. NOTE:
	 * field denoted in Java by camelCase convention are denoted in Postgres by
	 * snake_case convention by default (e.g. "userName" field in the Java class
	 * results in "user_name" DB column)
	 */
    @Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				// use the autowired datasource to access the saved credentials
				.dataSource(this.datasource)
				// retrieve username and role
				.authoritiesByUsernameQuery("SELECT user_name, role FROM credentials WHERE user_name=?")
				// retrieve username, password and a boolean flag specifying whether the user is
				// enabled or not (always enabled)
				.usersByUsernameQuery("SELECT user_name, password, 1 as enabled FROM credentials WHERE user_name=?");
	}

    /**
     * This method defines a "passwordEncoder" Bean.
     * The passwordEncoder Bean is used to encrypt and decrpyt the Credentials passwords.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}