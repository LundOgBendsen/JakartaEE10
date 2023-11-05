package dk.lundogbendsen.web;

import jakarta.inject.Inject;

public class BusinessServiceImpl implements BusinessService {

	@Inject @UpperCaseGreeting
	private String message;

	public String getMessageOfTheDay() {
		return message;
	}
}
