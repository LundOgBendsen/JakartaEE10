package dk.universitet.adm.utils;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import static dk.universitet.adm.utils.ThreadLocalEntityManager.*;

public class EntityManagerInjector {
	@PersistenceContext
	EntityManager em;

	@AroundInvoke
	public Object associate(InvocationContext ic) throws Exception {
		associateWithThread(em); // statically imported method
		try {
			return ic.proceed();
		} finally {
			cleanupThread(); // statically imported method
		}
	}
}
