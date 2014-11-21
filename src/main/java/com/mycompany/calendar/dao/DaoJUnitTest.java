package com.mycompany.calendar.dao;

import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import com.mycompany.calendar.domain.CalendarUser;
import com.mycompany.calendar.domain.Event;
import com.mycompany.calendar.domain.EventAttendee;
import com.mycompany.calendar.domain.EventLevel;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/root-context.xml")

public class DaoJUnitTest {
	@Autowired
	private CalendarUserDao calendarUserDao;	
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private EventAttendeeDao eventAttendeeDao;
	
	private CalendarUser[] calendarUsers = null;
	private Event[] events = null;
	private EventAttendee[] eventAttendees = null;
	
	private Random random = new Random(System.currentTimeMillis());

	private static final int numInitialNumUsers = 12;
	private static final int numInitialNumEvents = 4;
	private static final int numInitialNumEventAttendees = 12;
	
	@Before
	public void setUp() {
		calendarUsers = new CalendarUser[numInitialNumUsers];
		events = new Event[numInitialNumEvents];
		eventAttendees = new EventAttendee[numInitialNumEventAttendees];
		
		this.calendarUserDao.deleteAll();
		this.eventDao.deleteAll();
		this.eventAttendeeDao.deleteAll();
		
		for (int i = 0; i < numInitialNumUsers; i++) {
			calendarUsers[i] = new CalendarUser();
			calendarUsers[i].setEmail("user" + i + "@example.com");
			calendarUsers[i].setPassword("user" + i);
			calendarUsers[i].setName("User" + i);
			calendarUsers[i].setId(calendarUserDao.createUser(calendarUsers[i]));
		}
		
		for (int i = 0; i < numInitialNumEvents; i++) {
			events[i] = new Event();
			events[i].setSummary("Event Summary - " + i);
			events[i].setDescription("Event Description - " + i);
			events[i].setOwner(calendarUsers[random.nextInt(numInitialNumUsers)]);
			events[i].setEventLevel(EventLevel.NORMAL);
			switch (i) {				          /* Updated by Assignment 3 */
				case 0:
					events[i].setNumLikes(0);  							
					break;
				case 1:
					events[i].setNumLikes(9);
					break;
				case 2:
					events[i].setNumLikes(10);
					break;
				case 3:
					events[i].setNumLikes(10);
					break;
			}
			events[i].setId(eventDao.createEvent(events[i]));
		}
		
		for (int i = 0; i < numInitialNumEventAttendees; i++) {
			eventAttendees[i] = new EventAttendee();
			eventAttendees[i].setEvent(events[i % numInitialNumEvents]);
			eventAttendees[i].setAttendee(calendarUsers[i]);
			eventAttendees[i].setId(eventAttendeeDao.createEventAttendee(eventAttendees[i]));
		}
	}
	
	@Test
	public void getAllUsers() {
		// ?“±ë¡ëœ ëª¨ë“  Users ê°œìˆ˜ê°? numInitialNumUsers ?¸ì§? ?™•?¸?•˜?Š” ?…Œ?Š¤?Š¸ ì½”ë“œ  
		assertThat(this.calendarUserDao.findAllusers().size(), is(numInitialNumUsers));
	}
	
	@Test
	public void getAllEvents() {
		// ?“±ë¡ëœ ëª¨ë“  Events ê°œìˆ˜ê°? numInitialNumEvents ?¸ì§? ?™•?¸?•˜?Š” ?…Œ?Š¤?Š¸ ì½”ë“œ 
		assertThat(this.eventDao.findAllEvents().size(), is(numInitialNumEvents));
	}

	@Test
	public void getOneUserByEmail() {
		// email?´ 'user0@example.com'?¸ CalendarUserê°? ì¡´ìž¬?•˜?Š” ê²ƒì„ ?…Œ?Š¤?¬ 
		assertTrue(this.calendarUserDao.findUserByEmail("user0@example.com") != null);
	}
	
	@Test
	public void getTwoUserByEmail() {
		// partialEmail?´ 'user'?¸ CalendarUserê°? numInitialNumUsersëª…ìž„?„ ?™•?¸?•˜?Š” ?…Œ?Š¤?¬ ì½”ë“œ ?ž‘?„±
		assertThat(this.calendarUserDao.findUsersByEmail("user").size(), is(numInitialNumUsers));
	}
	
	@Test
	public void getAllEventAttendees() {
		// ê°? ?´ë²¤íŠ¸ ë³„ë¡œ ?“±ë¡ëœ Attendee ê°œìˆ˜ê°? 3?¸ì§? ?™•?¸?•˜?Š” ?…Œ?Š¤?Š¸ ì½”ë“œ
		for(int i=0; i<numInitialNumEvents; i++) {
			assertThat(this.eventAttendeeDao.findEventAttendeeByEventId(events[i].getId()).size(), is(3));
		}
	}
}