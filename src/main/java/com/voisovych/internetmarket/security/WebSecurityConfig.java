package com.voisovych.internetmarket.security;

import com.voisovych.internetmarket.servis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder())
                .and()
                .inMemoryAuthentication()
                .withUser("root")
                .password(bCryptPasswordEncoder()
                .encode("root"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/registration").anonymous()
                .antMatchers("/", "/type", "/search").hasAnyRole("USER", "SELLER", "ADMIN")
                .antMatchers("/edit", "/editsave").hasAnyRole("SELLER", "ADMIN")
                .antMatchers("/itemform", "/save", "/delete", "/users", "/adduser").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
