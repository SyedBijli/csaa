package csaa.csaa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.csaa.app.Application;
import com.csaa.app.model.Event;
import com.csaa.app.service.EventService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EventTest {
	
	@Autowired
	private EventService eventService;

    
    @Test
    public void eventTest() { 
    	ResponseEntity<List<Event>> eventList = eventService.getEvents("SyedBijli", "csaa", "CommitCommentEvent");
    	assertThat(!eventList.getBody().isEmpty());   
    }


	
}
