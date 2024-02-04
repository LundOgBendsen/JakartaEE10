package dk.lundogbendsen.jee7.cockpit;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.TransactionPhase;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Named
@ApplicationScoped
public class TxStatusListener {
	private List<TransactionStatus> success = new ArrayList<TransactionStatus>();
	private List<TransactionStatus> failure = new ArrayList<TransactionStatus>();
	
	@Inject
	SessionHandler sessionHandler;
	
	public void onInProgress(@Observes TransactionStatus status) {
		String s = "In progress: " + status.getMessage();
		System.out.println(s);
		JsonObject model = getJsonForTransactionStatus(status);
		System.out.println(model.toString());
		sessionHandler.sendToSessions(model.toString());
	}

	private JsonObject getJsonForTransactionStatus(TransactionStatus status) {
		JsonObject model = Json.createObjectBuilder()
				   .add("transactionStatus", Json.createObjectBuilder()
						      .add("timestamp", status.getTimeStamp().toString())
						      .add("message", status.getMessage())
						      .add("status", status.getResult().toString()))
				   .build();
		return model;
	}
	
	public void onSuccess(@Observes(during=TransactionPhase.AFTER_SUCCESS) TransactionStatus status) {
		status.setResult(ResultType.SUCCESS);		
		JsonObject model = getJsonForTransactionStatus(status);

		sessionHandler.sendToSessions(model.toString());
		System.out.println(model.toString());
		success.add(status);
	}
	
	public void onFailure(@Observes(during=TransactionPhase.AFTER_FAILURE) TransactionStatus status) {
		status.setResult(ResultType.FAILURE);		
		JsonObject model = getJsonForTransactionStatus(status);

		sessionHandler.sendToSessions(model.toString());
		String s = "Failed completing: " + status.getMessage();
		System.out.println(s);
		failure.add(status);
	}
	
	public void onBeforeCompletion(@Observes(during=TransactionPhase.BEFORE_COMPLETION) TransactionStatus status) {
		System.out.println("Before completion: " + status.getMessage());
	}

	public List<TransactionStatus> getSuccess() {
		return success;
	}

	public void setSuccess(List<TransactionStatus> success) {
		this.success = success;
	}

	public List<TransactionStatus> getFailure() {
		return failure;
	}

	public void setFailure(List<TransactionStatus> failure) {
		this.failure = failure;
	}
}
