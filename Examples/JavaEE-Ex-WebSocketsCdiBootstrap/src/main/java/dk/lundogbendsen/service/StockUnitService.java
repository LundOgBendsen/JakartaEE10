package dk.lundogbendsen.service;

import java.util.List;
import java.util.Random;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import dk.lundogbendsen.jee7.cockpit.TransactionStatus;
import dk.lundogbendsen.model.StockItem;

/**
 * Session Bean implementation class StockUnitService
 */
@Stateless
@LocalBean
public class StockUnitService {
	
	@Inject
	Event<TransactionStatus> txStatus;

	@PersistenceContext
	EntityManager em;


	public void create(StockItem item) {
		em.persist(item);
		txStatus.fire(new TransactionStatus("Created stock item " + item.getSku()));
	}
	
	public List<StockItem> findAll() {
		Query all= em.createNamedQuery("StockItem.findAll");
		List list = all.getResultList();
		if(new Random().nextDouble() <0.5) {
			txStatus.fire(new TransactionStatus("Failed reading StockItems!"));
			throw new RuntimeException("Expected Error");
		}
		txStatus.fire(new TransactionStatus("Read all stock items, count " + list.size()));
		return list;
	}
}
