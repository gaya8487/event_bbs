package com.event.bbs.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.bbs.web.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

	 List<Question> findByEvent_id(Integer eventId);


}
