package com.event.bbs.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter

@Builder
@ToString
public class QuestionDto {

	private Integer id;
	private String content;

	private Integer qNum;

	private Integer event_id;

}
