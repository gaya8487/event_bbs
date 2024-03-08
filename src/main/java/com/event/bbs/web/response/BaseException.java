package com.event.bbs.web.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class BaseException extends RuntimeException  {

	private final ErrorStatus errorStatus;

}
