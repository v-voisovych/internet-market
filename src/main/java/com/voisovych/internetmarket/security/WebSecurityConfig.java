package com.voisovych.internetmarket.security;

import com.voisovych.internetmarket.servis.CustomProvider;
import com.voisovych.internetmarket.servis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private CustomProvider customProvider;

    @Autowired
    public WebSecurityConfig(UserService userService, CustomProvider customProvider) {
        this.userService = userService;
        this.customProvider = customProvider;
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder())
                .and()
                .authenticationProvider(customProvider)
                .inMemoryAuthentication()
                .withUser("root")
                .password(bCryptPasswordEncoder()
                .encode("root"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration").anonymous()
                .antMatchers("/", "/type", "/search", "/allItemRest", "/deleteItemRest", "/findItemRest", "/addListItemsRest","/addItemRest", "/addBus", "/allBusses", "/deleteBus").hasAnyRole("USER", "SELLER", "ADMIN")
                .antMatchers("/edit", "/editsave").hasAnyRole("SELLER", "ADMIN")
                .antMatchers("/itemform", "/save", "/delete", "/users", "/adduser").hasRole("ADMIN")
                .anyRequest().authenticated()

//               !!!!!!!! REST SECURITY!!!!!!!!!!
                .and().httpBasic()
                .and().sessionManagement().disable()

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
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(customAccessDenied());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public CustomAccessDenied customAccessDenied() {
//        return new CustomAccessDenied();
//    }
}
