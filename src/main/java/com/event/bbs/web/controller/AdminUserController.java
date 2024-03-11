package com.event.bbs.web.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.bbs.web.service.AdminUserService;
import com.event.bbs.web.common.ErrorMessage;
import com.event.bbs.web.dto.requestDto.AdminUserFormDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class AdminUserController {

	private final AdminUserService adminUserService;

	@GetMapping("/create")
	public String create(@ModelAttribute("member") AdminUserFormDto adminUserFormDto){
		return "members/addMemberForm";
	}

	@PostMapping("/create")
	public String save(@Validated @ModelAttribute("member") AdminUserFormDto adminUserFormDto , BindingResult bindingResult){

			log.debug(adminUserFormDto.toString());

			if (bindingResult.hasErrors()){
				return "members/addMemberForm";
			}

			try{
				adminUserService.create(adminUserFormDto);
			}catch (DataIntegrityViolationException e){
				e.printStackTrace();
				bindingResult.reject(ErrorMessage.SIGN_UP_FAIL.getName(),ErrorMessage.SIGN_UP_FAIL.getExplain());
				return "members/addMemberForm";
			}

		return "redirect:/";

	}

}
