package com.event.bbs.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.event.bbs.entity.Event;
import com.event.bbs.entity.Question;
import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
class EventRepositoryTest {

	@Autowired
	private  EventRepository eventRepository;

	@Test
	@Transactional

	void saveEventTest(){

		Event event1 = Event.builder()
			.content("테스트 이벤트1")
			.createDate(LocalDate.now())
			.questionList(
				List.of(Question.builder().qNum(1).content("1번째 질문").build()))
			.build();

		Event result1 =  eventRepository.save(event1);

		log.info(result1.getContent());
		log.info(result1.getQuestionList().get(0).getContent());

		Assertions.assertThat(event1.getContent()).isEqualTo(result1.getContent());

	}


}
