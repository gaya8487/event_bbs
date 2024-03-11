package com.event.bbs.web.dto.requestDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class LoginFormDto {

	@Size(min=3, max=15)
	@NotEmpty
	private String loginId;

	@NotEmpty
	private String password;
}
