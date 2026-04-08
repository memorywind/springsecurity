package com.itheima.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity // 开启基于方法的授权
//@EnableWebSecurity // 开启springsecurity的自定义配置，springboot项目中可以省略这个注解
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //authorizeRequests()：开启授权保护

//        http.authorizeRequests(authorize ->
//                        //anyRequest()：对所有请求开启授权保护
//
//                        authorize.anyRequest()
//                                //authenticated()：已认证请求会自动被授权
//                                .authenticated())
//                .formLogin(form->{
//                    form.loginPage("/login").permitAll()
//                            .usernameParameter("myusername") // 配置自定义的表单用户名参数
//                            .passwordParameter("mypassword")
//                            .failureUrl("/login?failure"); // 配置自定义的表单密码参数
//                });//表单授权方式
//                //.httpBasic(withDefaults());//基本授权方式

        //authorizeRequests()：开启授权保护
        //anyRequest()：对所有请求开启授权保护
        //authenticated()：已认证请求会自动被授权
        //开启授权保护
        http.authorizeRequests(
                authorize -> authorize
                        //具有USER_LIST权限的用户可以访问/user/list
                        //.requestMatchers("/user/list").hasAuthority("USER_LIST")
                        //具有USER_ADD权限的用户可以访问/user/add
                        //.requestMatchers("/user/add").hasAuthority("USER_ADD")
                        //.requestMatchers("/user/**").hasRole("ADMIN")
                        //对所有请求开启授权保护
                        .anyRequest()
                        //已认证的请求会被自动授权
                        .authenticated()
        );

        http.formLogin(form -> {
            form         // 指定自定义登录页的路径
                    // 允许所有人访问登录页和登录提交接口
                    .successHandler(new MyAuthenticationSuccessHandler())
                    .failureHandler(new MyAuthenticationFailureHandler());
        });

        // 登出配置
        http.logout(logout -> {
            logout.logoutSuccessHandler(new MyLogoutSuccessHandler()); // 注销成功时的处理
        });


//        http.exceptionHandling(exception -> {
//            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint()); // 未认证时的处理
//        });

        http.exceptionHandling(exception -> {
            exception.accessDeniedHandler(new MyAccessDenideHandler());
        });

        //跨域
        http.cors(withDefaults());

        // 会话并发处理
        http.sessionManagement(session -> {
            session.maximumSessions(1).expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });

        // 关闭csrf防护
        //关闭csrf攻击防御
        http.csrf((csrf) -> {
            csrf.disable();
        });

        return http.build();
    }

    /*    @Bean
        public UserDetailsService userDetailsService() {
            // 创建基于内存的用户信息管理器
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            // 创建userdetial类型的对象，用来管理用户名、密码、用户信息、用户权限等内容
            manager.createUser(User.withDefaultPasswordEncoder().username("wanghao").password("password").roles("USER").build());
            return manager;
        }*/
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // 创建基于数据库的用户信息管理器
//        DBUserDetailsManager manager = new DBUserDetailsManager();
//        return manager;
//    }
}
