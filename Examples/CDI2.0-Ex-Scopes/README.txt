CDI2.0-Ex-Scopes

Demonstrates different scopes.

Open the class dk/lundogbendsen/web/BusinessServiceImpl.java.
Try the 3 different scope annotations by commenting line 14-16 in / out.

How to run:
Eclipse: Run the project on the server. Eclipse's browser will
display the welcome page. 

Or

'''
mvn install -P mvn-deploy
'''

Then open a browser and go to: http://localhost:8080/CDI2.0-Ex-Scopes-1.0-SNAPSHOT/index.html

Try refreshing. Observe the counter.
Try opening the URL in a different browser. Watch how a SessionScoped class behave.

