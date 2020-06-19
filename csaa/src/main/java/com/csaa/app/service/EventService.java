package com.csaa.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.csaa.app.model.Event;

public interface EventService {
	public ResponseEntity<List<Event>> getEvents(String owner, String repo, String type );
}
