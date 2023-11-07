package dk.lundogbendsen.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CourseDescription {
	private String id;
	private String name;
	private String description;
	private int duration;

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Path("{index}")
	public Course getCourse(@PathParam("index") String index) {
		System.out.println("CD.getCourse: " + index);
		return getCourses().get(Integer.parseInt(index));
	}
	
	@GET
	public CourseDescription get() {
		return this;
	}
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	private List<Course> courses = new ArrayList<Course>();

	public CourseDescription() {
	}

	public CourseDescription(String id, String name, String description,
			int duration) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.duration = duration;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
