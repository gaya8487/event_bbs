package com.event.bbs.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		//로그인 없이도 모든 url 접근 가능
		http.authorizeHttpRequests((authorizeHttpRequests) ->  authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
			//h2-console url에는  csrf 토큰 확인 무시
			.csrf((csrf)->csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
			//header, x-frame-options 헤더 기본은 Deny 이지만 'SAMEORIGIN'인 경우에만 허락
			.headers((headers)-> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));
		
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
}
