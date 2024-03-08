package com.event.bbs.web.dto;

import java.time.LocalDate;
import java.util.List;

import com.event.bbs.web.entity.Answer;
import com.event.bbs.web.entity.Player;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class EventDto {

	private Integer id;

	private String subject;

	private String content;

	private LocalDate createDate;

	private List<QuestionDto> questionList;

	private List<Answer> answerList;

	private List<Player> playerList;

}
