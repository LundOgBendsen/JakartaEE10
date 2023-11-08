package dk.lundogbendsen.rest.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CourseEvent {
	private int id;
	private Date start;
	private Date end;
	private Location location;
	private Room room;

	private List<Student> students = new ArrayList<Student>();

	public CourseEvent() {
		// TODO Auto-generated constructor stub
	}

	public CourseEvent(int id, Date start, Date end, Location location, Room room) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.location = location;
		this.room = room;
	}

	@GET
	public CourseEvent get() {
		return this;
	}

	
	@Path("students/{index}")
	public Student getStudent(@PathParam("index") String index ) {
		return this.getStudents().get(Integer.parseInt(index));
	}
	
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Student> getStudents() {
		return students;
	}
}
