package com.event.bbs.web.response;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public String baseErrorException(Model model,BaseException e) {

		int code = e.getErrorStatus().getCode();
		String description = e.getErrorStatus().getDescription();

		model.addAttribute("error",BaseResponse.error(code,description));

		return "error_page";
	}

}
