package com.event.bbs.dto.requestDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionReqDto {

		private int qNum;
		private String subject;

}
