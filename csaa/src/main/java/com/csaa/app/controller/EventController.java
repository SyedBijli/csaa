package com.csaa.app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.csaa.app.model.Event;

@RestController
@RequestMapping("/csaa")
public class EventController {
	
	@GetMapping(value="/events/{owner}/{repo}/{type}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public Event getEvents(@PathVariable String owner, 
			@PathVariable String repo, 
			@PathVariable String type) {	
		
		Event event = null; 
		RestTemplate restTemplate = new RestTemplate();
		String uri  = "https://api.github.com/repos/"+owner+"/"+repo+"/"+type;

	    
	    String result = restTemplate.getForObject(uri, String.class);
	    System.out.println(result);
	    return event;
	}
}
