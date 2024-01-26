package com.event.bbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.event.bbs.entity.Event;
import com.event.bbs.repository.EventRepository;
import com.event.bbs.response.BaseException;
import com.event.bbs.response.ErrorStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventService {

	private final EventRepository eventRepository;

	public List<Event> getEventList(){
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


}
