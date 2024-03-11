package com.event.bbs.web.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@ToString
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length =200)
	private String subject;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDate createDate;

	@OneToMany(mappedBy = "event",cascade = CascadeType.REMOVE)
	private List<Question> questionList;

	@OneToMany(mappedBy = "event",cascade = CascadeType.REMOVE)
	private List<Answer> answerList;

	@OneToMany(mappedBy = "event",cascade = CascadeType.REMOVE)
	private List<Player> playerList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private AdminUser adminUser;

}
