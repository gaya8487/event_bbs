package com.event.bbs.web.common.filter;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logfilter implements Filter {

  //filter를 implements 받아 필터를 만듬

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
    log.info("log filter init");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    log.info("log filter doFilter");

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String requestURI = httpServletRequest.getRequestURI();

    String uuid = UUID.randomUUID().toString();

    try{

      log.info("Request [{}{}]",uuid, requestURI);
      
      //doFilter에 값이 있으면 다음 필터 호출
      //없으면 서블릿 호출
      chain.doFilter(request,response);

    }catch (Exception e){
      throw e;
    }finally {
      log.info("Response [{}{}]",uuid, requestURI);
    }
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
