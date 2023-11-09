/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.universitet.adm.indskrivning.control;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/*
 * 
 * Control: superklasse for dao-controls, som tilføjer basal CRUD-logik
 */
public abstract class AbstractDAOControl<T> {
	
	//Alternativ: brug GoF-template og lav en getEntityManager()-metode, som kan overskrives i subklasser.
	@PersistenceContext(unitName="primary")	
	protected EntityManager em;
	
	private Class<T> entityClass;
	
    public AbstractDAOControl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(T entity) {
        em.persist(entity);
    }

    public void edit(T entity) {
        em.merge(entity);
    }

    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

    public T find(Object id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        jakarta.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        jakarta.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        jakarta.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
