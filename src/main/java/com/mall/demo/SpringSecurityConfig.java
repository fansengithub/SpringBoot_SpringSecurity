package com.mall.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private  MyUserService myUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //inMemoryAuthentication 从内存中获取
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("test").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("demo").password(new BCryptPasswordEncoder().encode("demo")).roles("USER");

//        auth.userDetailsService(myUserService).passwordEncoder(new MyPasswordEncoder());

        auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("").passwordEncoder(new MyPasswordEncoder());
    }


    @Override
    protected  void  configure(HttpSecurity httpSecurity) throws  Exception{
            httpSecurity.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .logout().permitAll()
                    .and()
                    .formLogin();
            httpSecurity.csrf().disable();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws  Exception{
     webSecurity.ignoring().antMatchers("/js/**","/css/**","/images/**");
  }

}
