package dk.lundogbendsen.web;

import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@RequestScoped
public class Messages implements Serializable {

	public void add(String message) {
		FacesMessage m = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, m);
	}
}
