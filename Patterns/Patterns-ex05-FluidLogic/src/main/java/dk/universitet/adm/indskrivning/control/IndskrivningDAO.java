/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.universitet.adm.indskrivning.control;

import java.util.Random;

import dk.universitet.adm.model.Studerende;

/**
 *
 * @author jakob
 * 
 * Viser en Control. Al logik nedarves fra superklassen. 
 */
public class IndskrivningDAO extends AbstractDAOControl<Studerende> {
     
    public IndskrivningDAO() {
    	super(Studerende.class);
	}
    
    public Studerende indskriv(Studerende studerende) {
    	if (studerende.getStudienummer()!=null) {
			throw new IllegalArgumentException("Studerende har allerede et studienummer og kan ikke indskrives!");
		}
		if (studerende.getId()!=null) {
			throw new IllegalArgumentException("Studerende har allerede en id (" + studerende.getId() + ")");			
		}
		studerende.setStudienummer(getStudienummer());
		this.create(studerende);
		
		return studerende;

	}
    
	//Simulerer dannelsen af et nyt studienummer på 10 cifre med 0-padding.
	private String getStudienummer() {		
		return String.format("%010d", new Random().nextInt(1000000000));
	}

}
