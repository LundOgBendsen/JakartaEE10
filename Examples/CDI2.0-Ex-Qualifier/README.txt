CDI2.0-Ex-Qualifier

Demonstrates the use of CDI Qualifier annotations.

Note the injection point in BusinessServiceImpl:

	@Inject @UpperCaseGreeting
	private String message;
	
The @UpperCaseGreeting is also found at the producer methods
in CdiFactory. 

How to run:
Run the project on the server. Eclipse's browser will
display the welcome page. 

Or

'''
mvn install -P mvn-deploy
'''
