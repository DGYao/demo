package com.springboot.web;

import com.springboot.web.security.AdminUsernamePasswordFilter;
import com.springboot.web.security.CustomUserDetailsService;
import com.springboot.web.security.LoginFaillureHandler;
import com.springboot.web.security.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.session.SessionManagementFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter{
    //认证失败处理
    @Autowired
    private LoginFaillureHandler loginFaillureHandler;
    //认证成功处理
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    //用户认证
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("yao").password("123").roles("yao");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
//                .failureHandler(loginFaillureHandler)
//                .successHandler(loginSuccessHandler)
//                //跳转地址要在handler下面，生成对象，再设置值
//                .failureUrl("/login?error=1")
//                .defaultSuccessUrl("/index")
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=1");
        http
                .addFilter(rememberMeAuthenticationFilter())
                .addFilter(adminUsernamePasswordFilter())
                .addFilter(concurrentSessionFilter())
                .sessionManagement()
                .sessionAuthenticationStrategy(compositeSessionAuthenticationStrategy())
                .invalidSessionUrl("/login?expired=1");
    }

    //登录认证filter
    @Bean
    protected AdminUsernamePasswordFilter adminUsernamePasswordFilter(){
        AdminUsernamePasswordFilter filter = new AdminUsernamePasswordFilter();
        filter.setSessionAuthenticationStrategy(compositeSessionAuthenticationStrategy());
        filter.setAuthenticationManager(authenticationManager());
        loginSuccessHandler.setDefaultTargetUrl("/index");
        filter.setAuthenticationSuccessHandler(loginSuccessHandler);
        loginFaillureHandler.setDefaultFailureUrl("/login?error=1");
        filter.setAuthenticationFailureHandler(loginFaillureHandler);
        filter.setRememberMeServices(rememberMeServices());
        return filter;
    }

    //sessionfilter
    @Bean
    protected ConcurrentSessionFilter concurrentSessionFilter(){
        return new ConcurrentSessionFilter(sessionRegistry());
    }

    //记住密码filter
    @Bean
    protected RememberMeAuthenticationFilter rememberMeAuthenticationFilter(){
        return new RememberMeAuthenticationFilter(authenticationManager(),rememberMeServices());
    }

    //记住密码设置
    @Bean
    protected RememberMeServices rememberMeServices(){
        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices("ez",customUserDetailsService);
        //设置记住密码一周
        tokenBasedRememberMeServices.setTokenValiditySeconds(604800);
        //设置cookie名称
        tokenBasedRememberMeServices.setCookieName("ez_rm");
        return tokenBasedRememberMeServices;
    }

    //session设置
    @Bean
    protected CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy(){
        List<SessionAuthenticationStrategy> list = new ArrayList<>();
        //并发控制
        ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        concurrentSessionControlAuthenticationStrategy.setMaximumSessions(1);//单一登录
        concurrentSessionControlAuthenticationStrategy.setExceptionIfMaximumExceeded(false);//是否保存旧session
        //固化防护，控制认证前后jesessionid改变,生成新session(默认属性也改变：migrateSessionAttributes控制)
        SessionFixationProtectionStrategy sessionFixationProtectionStrategy = new SessionFixationProtectionStrategy();
        //认证后保存sessionid到用户的信息里
        RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy = new RegisterSessionAuthenticationStrategy(sessionRegistry());
        list.add(concurrentSessionControlAuthenticationStrategy);
        list.add(sessionFixationProtectionStrategy);
        list.add(registerSessionAuthenticationStrategy);
        return new CompositeSessionAuthenticationStrategy(list);
    }

    @Bean
    protected SessionRegistryImpl sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Bean
    protected AuthenticationManager authenticationManager(){
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(daoAuthenticationProvider());
        providers.add(rememberMeAuthenticationProvider());
        ProviderManager providerManager = new ProviderManager(providers);
        return providerManager;
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        ReflectionSaltSource reflectionSaltSource = new ReflectionSaltSource();
        //设置userDetails盐值的属性名
        reflectionSaltSource.setUserPropertyToUse("salt");
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setSaltSource(reflectionSaltSource);
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new Md5PasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    protected RememberMeAuthenticationProvider rememberMeAuthenticationProvider(){
        return new RememberMeAuthenticationProvider("ez");
    }

}
