package dk.universitet.adm.indskrivning.boundary;

import java.util.Random;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.interceptor.Interceptors;

import dk.universitet.adm.indskrivning.entity.Studerende;
import dk.universitet.adm.utils.EntityManagerInjector;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors( EntityManagerInjector.class)
public class Indskrivning {
	Logger log = Logger.getLogger(Indskrivning.class.getName());
	
	public Studerende indskrivStuderende(Studerende studerende) {
		//Entity: Entiteten indeholder selv forretningslogikken til indskrivning. 
		return studerende.indskriv();
	}
	
	//Simulerer dannelsen af et nyt studienummer på 10 cifre med 0-padding.
	private String getStudienummer() {		
		return String.format("%010d", new Random().nextInt(1000000000));
	}
}
