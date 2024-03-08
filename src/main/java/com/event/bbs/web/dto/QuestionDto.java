package com.event.bbs.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter

@Builder
@ToString
public class QuestionDto {

	private Integer id;
	private String content;

	private Integer qNum; //삭제 예정

	private Integer event_id;

}
