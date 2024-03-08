package com.event.bbs.web.response;

import java.io.Serializable;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force = true)
public class BaseResponse<T> implements Serializable {
	private final int code;
	private final String message;
	private final T data;

	private BaseResponse(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	//error message without data
	public static BaseResponse error(int code, String message){
		return new BaseResponse<>(code,message,null);
	}

	//error message with data
	public  static <T> BaseResponse<T> error(int code, String message, T data) {
		return new BaseResponse<>(code, message, data);
	}

}
