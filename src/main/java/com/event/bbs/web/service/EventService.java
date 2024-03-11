package com.event.bbs.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.event.bbs.web.entity.Event;
import com.event.bbs.web.repository.EventRepository;
import com.event.bbs.web.response.BaseException;
import com.event.bbs.web.response.ErrorStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

	private final EventRepository eventRepository;

	public List<Event> getAllEventList(){
		List<Event> eventList = this.eventRepository.findAll();
		return eventList;
	}

	public Event getEvent(int id) {

		Optional<Event> event = eventRepository.findById(id);

		if (event.isPresent()) {
			return event.get();
		} else {
			throw new BaseException(ErrorStatus.DATA_NOT_FOUND);
		}
	}

	public List<Event> getEventListByAdminUserId(int userId) {

		return eventRepository.findByAdminUser_Id(userId);

	}
}
