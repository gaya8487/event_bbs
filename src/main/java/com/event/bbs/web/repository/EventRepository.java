package com.event.bbs.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.bbs.web.entity.Event;

public interface EventRepository  extends JpaRepository<Event,Integer> {
}
