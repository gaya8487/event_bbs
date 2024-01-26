package com.event.bbs.dto;

import java.time.LocalDate;
import java.util.List;

import com.event.bbs.entity.Answer;
import com.event.bbs.entity.Player;
import com.event.bbs.entity.Question;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
