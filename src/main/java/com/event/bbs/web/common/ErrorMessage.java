package com.event.bbs.web.common;

public enum ErrorMessage {


	LOGIN_FAIL("로그인 실패","아이디 또는 비밀번호가 맞지 않습니다"),
	SIGN_UP_FAIL("회원 등록 실패","이미 등록된 사용자 입니다."),

	INPUT_ERROR("입력 내용 에러","입력 내용을 확인해주세요");


	private final String name;
	private final String explain;


	ErrorMessage(String name,String explain) {
		this.name = name;
		this.explain = explain;
	}

	public String getExplain(){
		return explain;
	}
	public String getName(){
		return name;
	}



}
