package com.event.bbs.web.repository;

import com.event.bbs.web.entity.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository  extends JpaRepository<Event,Integer> {

  List<Event> findByAdminUser_Id(int adminUserId);

}
