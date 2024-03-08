package com.event.bbs.web.dto.requestDto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserFormDto {

	@NotNull
	private String loginId;

	@NotNull
	private String name;

	@NotNull
	private String password;

	@NotNull
	private String email;
}
