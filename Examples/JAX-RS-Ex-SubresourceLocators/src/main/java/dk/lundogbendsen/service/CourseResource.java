package dk.lundogbendsen.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import dk.lundogbendsen.rest.domain.CourseDescription;
import dk.lundogbendsen.rest.domain.CourseEvent;
import dk.lundogbendsen.rest.domain.Location;
import dk.lundogbendsen.rest.domain.Room;
import dk.lundogbendsen.rest.domain.Student;

@Path("/courses")
public class CourseResource {
	private Map<String, CourseDescription> courses = new HashMap<String, CourseDescription>();

	public CourseResource() {
		init();
	}
	
	@Path("{id}")
	public CourseDescription getCourse(@PathParam("id") String id) {
		System.out.println("GET course, id=" + id);
		CourseDescription courseDescription = courses.get(id);
		if (courseDescription == null) {
			throw new IllegalArgumentException("Not found");
		}
		return courseDescription;
	}
	
	
	@SuppressWarnings("deprecation")
	private void init() {
		System.out.println("Initializing course base...");
		Location regus = new Location("Regus", "Lars Bjoernsstraede 10", "1415",
				"Copenhagen");
		Room room1 = new Room(1, "1-1", 12);
		Room room2 = new Room(2, "1-2", 10);
		regus.getRooms().add(room1);
		regus.getRooms().add(room2);
		CourseDescription courseDescription = new CourseDescription("LB1300",
				"Basic Java 6", "An introductory course in Java 6", 5);
		CourseEvent course = new CourseEvent(1, new Date(2009, 1, 1),
				new Date(2009, 1, 3), regus, room1);
		courseDescription.getCourses().add(course);
		Student student = new Student(1, "Sophie Jensen", "RockIT",
				"Nansensgade 1", "2200", "Copenhagen");
		course.getStudents().add(student);
		courses.put(courseDescription.getId(), courseDescription);
	}
}
