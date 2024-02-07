package dk.university.adm.enrollment.boundary;

import dk.university.adm.model.Student;
import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

//Gateway: a stateful session bean that can hold an instance os Student and let the client work on it over multiple calls.
@Stateful
//Gateway: methods are not transactional since we do not want the EntityManager to flush for each client call.
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
//Gateway: dependent is necessary to enable calling an @Remove annotated method from the client.
@Dependent
public class StudentGateway {
	@PersistenceContext(unitName = "pu", type = PersistenceContextType.EXTENDED)
	EntityManager em;

	Student current;
	
	public StudentGateway() {
	}

	public Student find(long id) {
		this.current = this.em.find(Student.class, id);
		return this.current;
	}

	public Student getCurrent() {
		return current;
	}

	public void create(Student student) {
		this.em.persist(student);
		this.current = student;
	}

	
	public void remove(long id) {
		Student ref = this.em.getReference(Student.class, id);
		this.em.remove(ref);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save() {
	}
	
	@Remove
	public void closeGateway() {
	}

}
