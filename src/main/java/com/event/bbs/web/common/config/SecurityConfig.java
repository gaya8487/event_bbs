package com.event.bbs.web.common.config;

import com.event.bbs.web.common.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {


	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		log.info("SecurityConfig 시작");

		//로그인 없이도 모든 url 접근 가능
		http.authorizeHttpRequests((authorizeHttpRequests) ->  authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
			//h2-console url에는  csrf 토큰 확인 무시
			.csrf((csrf)->csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
			//header, x-frame-options 헤더 기본은 Deny 이지만 'SAMEORIGIN'인 경우에만 허락
			.headers((headers)-> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
				//로그인 설정
				.formLogin((formLogin)-> formLogin
						.loginPage("/login")
						.usernameParameter("loginId")
						.defaultSuccessUrl("/event/list")
						.failureUrl("/login?error")
				)
				.logout((logout)->logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.deleteCookies(SessionConst.JSESSIONID)
						.invalidateHttpSession(true));

		return  http.build();
	}

	/**
	 * 비밀번호 암호용 Encoder
	 * @return
	 */
	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}


}
