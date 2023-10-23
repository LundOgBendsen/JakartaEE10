package dk.lundogbendsen.ejb;

import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Singleton
@Startup
public class InitializeDatabaseBean {

	Logger log = Logger.getLogger(InitializeDatabaseBean.class.getSimpleName());

	@PersistenceContext(unitName = "pu")
	EntityManager em;

	@PostConstruct
	public void init() {
		//her kan du oprette og gemme ingredienser
	}

}
