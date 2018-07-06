package kr.co.redbrush.webapp.config;

import kr.co.redbrush.webapp.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider)
                .userDetailsService(accountServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers(
                    "/css/**",
                    "/font/**",
                    "/images/**",
                    "/js/**",
                    "/vendors/**"
                );
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // TODO : Use below code to disable csrf.
        /*httpSecurity.csrf()
                .disable()
                .authorizeRequests()*/
        httpSecurity.authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin/signup").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login/form")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login/form?error=true")
                        .usernameParameter("id")
                        .passwordParameter("password")
                .and()
                    .headers()
                        .frameOptions()
                            .disable()
                .and()
                    .csrf()
                        .ignoringAntMatchers("/h2/**")
                .and()
                    .logout()
                        .logoutUrl("/logout");
    }
}
