package com.event.bbs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.bbs.entity.Event;
import com.event.bbs.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {


	public List<Question> findByEvent_id(Integer eventId);


}
