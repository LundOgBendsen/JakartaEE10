package dk.university.adm.enrollment.boundary;

import java.util.logging.Logger;

import dk.university.adm.enrollment.entity.Student;
import dk.university.adm.utils.EntityManagerInjector;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.interceptor.Interceptors;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors( EntityManagerInjector.class)
public class Enrollment {
	Logger log = Logger.getLogger(Enrollment.class.getName());
	
	public Student enroll(Student student) {
		//Entity: the entity itself contains the business logic
		return student.enroll();
	}
}
