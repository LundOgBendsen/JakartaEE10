package dk.lundogbendsen.service;

import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import dk.lundogbendsen.domain.Course;
import dk.lundogbendsen.domain.CourseDescription;
import dk.lundogbendsen.domain.Location;
import dk.lundogbendsen.domain.Room;
import dk.lundogbendsen.domain.Student;
import jakarta.ws.rs.core.Context;

@Path("/courses")
public class CourseResource {
	private Map<String, CourseDescription> courses = new HashMap<String, CourseDescription>();

	public CourseResource() {
		init();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public CourseDescription getCourseAsXml(@PathParam("id") String id) {
		System.out.println("GET course as xml, id=" + id);
		CourseDescription courseDescription = courses.get(id);
		if (courseDescription == null) {
			throw new IllegalArgumentException("Not found");
		}
		return courseDescription;
	}
	
	@GET
	@Path("{id}")
	@Produces("text/plain")
	public String getCourseAsText(@PathParam("id") String id) {
		System.out.println("GET course as text, id=" + id);
		CourseDescription courseDescription = courses.get(id);
		if (courseDescription == null) {
			throw new IllegalArgumentException("Not found");
		}
		return String.format("Course: %s, %s, %s days", courseDescription.getId(), courseDescription.getName(), courseDescription.getDuration());
	}

	@Context
	ServletContext servletContext;

	@GET
	@Path("{id}")
	@Produces("image/gif")
	public InputStream getCourseAsJpg(@PathParam("id") String id) {

		System.out.println("GET course as gif, id=" + id);
		CourseDescription courseDescription = courses.get(id);
		if (courseDescription == null) {
			throw new IllegalArgumentException("Not found");
		}
		InputStream is = servletContext.getResourceAsStream("/dragon.gif");
		return is;
	}

	
	@SuppressWarnings("deprecation")
	private void init() {
		System.out.println("Initializing course base...");
		Location regus = new Location("Regus", "Lars Bj�rnsstr�de 10", "1415",
				"Copenhagen");
		Room room1 = new Room(1, "1-1", 12);
		Room room2 = new Room(2, "1-2", 10);
		regus.getRooms().add(room1);
		regus.getRooms().add(room2);
		CourseDescription courseDescription = new CourseDescription("LB1300",
				"Basic Java 6", "An introductory course in Java 6", 5);
		Course course = new Course(1, new Date(2009, 1, 1),
				new Date(2009, 1, 3), regus, room1);
		courseDescription.getCourses().add(course);
		Student student = new Student(1, "Ib Jensen", "RockIT",
				"Nansensgade 1", "2200", "Copenhagen");
		course.getStudents().add(student);
		courses.put(courseDescription.getId(), courseDescription);
	}
}
