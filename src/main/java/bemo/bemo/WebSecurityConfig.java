package bemo.bemo;

import bemo.bemo.auth.CustomOAuth2UserService;
import bemo.bemo.service.PrincipalDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@RequiredArgsConstructor
@EnableWebSecurity // spring security 활성화
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig{


    /**
     * Spring Security 5.7.x 부터 WebSecurityConfigurerAdapter 는 Deprecated.
     * -> SecurityFilterChain, WebSecurityCustomizer 를 상황에 따라 빈으로 등록해 사용한다.
     */
    private PrincipalDetailsServiceImpl principalDetailsServiceImpl;
    private CustomOAuth2UserService customOAuth2UserService;


    @Autowired
    public WebSecurityConfig(PrincipalDetailsServiceImpl principalDetailsServiceImpl, CustomOAuth2UserService customOAuth2UserService) {
        this.principalDetailsServiceImpl = principalDetailsServiceImpl;
        this.customOAuth2UserService = customOAuth2UserService;

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login","/profile", "/signup", "/", "/review", "/searchfirst","/search", "/hashtag",
                        "/ticketmain", "/send", "/send2","/join", "/error", "/loginSuccess","/moviedetail",
                        "/moviedetail/{detail}","/moviedetail/{detail}/{code}","/{movie_title}/review",
                        "/{movie_title}/review/posts","/comments/{id}","/hello","/api/upload","/api/list",
                        "/modify/{id}","/users/{id}","/users").permitAll() // 누구나 접근 허용
                .antMatchers("/user").hasRole("USER") // USER, ADMIN만 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                .formLogin()
                .loginPage("/") // 로그인 페이지 링크
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                .failureForwardUrl("/error")
                .and()
                .logout()
                .logoutUrl("/logout")			// 로그아웃을 처리할 URL 입력
                .logoutSuccessUrl("/")  // 로그아웃 성공시 리다이렉트 주소
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true) // 세션 날리기
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
        ;
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);
        return http.build();
    }

    @Bean
    public static BCryptPasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(principalDetailsServiceImpl)
                .passwordEncoder(getPasswordEncoder())
                .and()
                .build();
    }
}