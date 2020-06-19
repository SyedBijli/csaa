package com.csaa.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csaa.app.model.Event;
import com.csaa.app.service.EventService;

@RestController
@RequestMapping("/csaa")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping(value="/events/{owner}/{repo}/{type}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Event>> getEvents(
			@PathVariable String owner, 
			@PathVariable String repo, 
			@PathVariable String type) {	
		
		return eventService.getEvents(owner, repo, type);
		
		
	}
}
