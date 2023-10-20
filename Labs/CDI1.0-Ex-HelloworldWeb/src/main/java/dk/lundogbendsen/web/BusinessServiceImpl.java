package dk.lundogbendsen.web;

import jakarta.inject.Inject;

public class BusinessServiceImpl implements BusinessService {

	@Inject
	private String message;

	public String getMessageOfTheDay() {
		return message;
	}
}
