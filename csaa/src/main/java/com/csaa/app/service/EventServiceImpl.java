package com.csaa.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.csaa.app.model.Actor;
import com.csaa.app.model.Event;

public class EventServiceImpl implements EventService{

	@Override
	public ResponseEntity<List<Event>> getEvents(String owner, String repo, String type) {
		Event event = null; 
		List<Event> eventList = null;
		
		//invoking the git api. 
		RestTemplate restTemplate = new RestTemplate();
		String uri  = "https://api.github.com/repos/"+owner+"/"+repo+"/events";	    
	    String resp = restTemplate.getForObject(uri, String.class);
	    
	    //parsing the resonse json.
	    JsonParser springParser = JsonParserFactory.getJsonParser();
	    List<Object> list = springParser.parseList(resp);
	    eventList = new ArrayList<Event>();
	    for(Object o : list) {
	    	
	    	if(o instanceof Map) {
	    		@SuppressWarnings("unchecked")
				Map<String,Object> map = (Map<String,Object>) o;	    		
	    		event = new Event();	    		
	    		
	    		for (Map.Entry<String, Object> entry : map.entrySet()) {
	    			if(entry.getKey().contentEquals("actor")) {
	    				Actor actor = new Actor();
	    				actor.setActor(entry.getValue());
	    				event.setActor(actor);
	    			}	  
	    			if(entry.getKey().contentEquals("type")) {
	    				event.setType(entry.getValue().toString());
	    			}
	    			if(entry.getKey().contentEquals("created_at")) {
	    				event.setTimeStamp(entry.getValue().toString());
	    			}
	    			eventList.add(event);
	    		}	    		
	    	}	    	
	    }
	    
	    if (resp.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    } else {	    	   	
	        return ResponseEntity.ok(eventList);
	    }
	}

}
