package dk.lundogbendsen.web;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Denne klasse wrapper blot en conversation, tilføjer @Named og stiller metoder
 * til rådighed så man kan undersøge konversationen fra view'et af.
 */
@RequestScoped
@Named("conv")
public class ConversationWrapper {

	public ConversationWrapper() {
	System.out.println("ny ConversationWrapper ");
	}
	
	@Inject
	Conversation conversation;

	public boolean isRunning() {
		return !conversation.isTransient();
	}

	public void begin() { 
		conversation.begin();
	}

	public void end() {
		conversation.end();
	}

	public String getId() {
			return conversation.getId();
	}
}
