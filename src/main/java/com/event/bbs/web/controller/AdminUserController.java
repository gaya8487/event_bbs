package com.event.bbs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.bbs.web.dto.requestDto.AdminUserFormDto;
import com.event.bbs.login.service.AdminUserService;

import lombok.RequiredArgsConstructor;

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
	public String save( @ModelAttribute AdminUserFormDto adminUserFormDto , BindingResult bindingResult){

		adminUserFormDto.toString();

		if (bindingResult.hasErrors()){
			return "members/addMemberForm";
		}

		adminUserService.create(adminUserFormDto);
		return "home";

	}

}
