package com.event.bbs.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorStatus  {

	DATA_NOT_FOUND(404,"Data not Found");

	private final int code;
	private final String description;


	public int getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.description;
	}
}
