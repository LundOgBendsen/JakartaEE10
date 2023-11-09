package dk.universitet.adm.indskrivning.boundary;

import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import dk.universitet.adm.indskrivning.control.IndskrivningDAO;
import dk.universitet.adm.model.Studerende;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class Indskrivning {
	Logger log = Logger.getLogger(Indskrivning.class.getName());
	
	//Control: her bruger Broundary'en en Control.
	@Inject
	IndskrivningDAO dao;

	public Studerende indskrivStuderende(Studerende studerende) {
		dao.indskriv(studerende);
		log.info("Studerende er indskrevet");
		return studerende;
	}
	
}
