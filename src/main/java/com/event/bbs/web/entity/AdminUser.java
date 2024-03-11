package com.event.bbs.web.entity;

import java.util.List;

import com.event.bbs.web.entity.Event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
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
@ToString
@Entity
public class AdminUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(unique = true)
	private String loginId;

	@NotEmpty
	private  String name;

	@NotEmpty
	private String password;

	@NotEmpty
	@Column(unique = true)
	private String email;

	@OneToMany(mappedBy = "adminUser")
	private List<Event> eventList;



}
