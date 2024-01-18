package com.event.bbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.bbs.entity.Event;

public interface EventRepository  extends JpaRepository<Event,Integer> {
}
