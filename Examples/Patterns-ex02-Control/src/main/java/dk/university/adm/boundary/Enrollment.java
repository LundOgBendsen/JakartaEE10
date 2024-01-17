package dk.university.adm.boundary;

import java.util.logging.Logger;

import dk.university.adm.control.EnrollmentDAO;
import dk.university.adm.model.Student;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class Enrollment {
	Logger log = Logger.getLogger(Enrollment.class.getName());
	
	//Control: here the Boundary uses a Control
	@Inject
	EnrollmentDAO dao;

	public Student enrollStudent(Student student) {
		dao.enroll(student);
		log.info("Student has been enrolled");
		return student;
	}
	
}
