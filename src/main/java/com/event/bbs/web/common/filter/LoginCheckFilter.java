package com.event.bbs.web.common.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j
public class LoginCheckFilter implements Filter {
  //filter를 implements 받아 필터를 만듬

  private static  final String[] whitelist = {"/","members/create","/login","logout","/css/*"};
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();

    HttpServletResponse httpResponse = (HttpServletResponse) response;

    //spring security로 인증받은 인증 정보
    Principal principal = httpRequest.getUserPrincipal();

    try{
      log.info("인층 체크 필터 시작{}", requestURI);

      if (isLoginCheckPath(requestURI)) {

        log.info("인증 체크 로직 실행{}",requestURI);
        HttpSession session = httpRequest.getSession(false);

        //if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
        
        log.info("httpRequest.getUserPrincipal 인증 {} ", principal);
        //인증 정보가 있는지 확인
        if (principal == null) {

          log.info("미인증 사용자 요청 {}",requestURI);
          //로그인으로 redirect
          httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
          return; //여기가 중요, 미인증 사용자는 다음으로 진행하지 않고 끝
        }

      }
      chain.doFilter(request, response);
    }catch (Exception e) {
      throw e; //예외 로깅 가능 하지만, 톰캣까지 예외를 보내주어야 함
    } finally {
      log.info("인증 체크 필터 종료 {}", requestURI);
    }
  }
  private boolean isLoginCheckPath(String requestURI){

    return !PatternMatchUtils.simpleMatch(whitelist,requestURI);
  }


}
