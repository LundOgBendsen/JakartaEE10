var socket = new WebSocket("ws://localhost:8080/JavaEE-Ex-WebSocketsCdiBootstrap-1.0-SNAPSHOT/wsserver");
socket.onmessage = onMessage;

function onMessage(event) {
	var message = event.data;
	printMessageElement(message);
}


function printMessageElement(message) {
	
	var content = document.getElementById("transactionLog");

	var transactionStatusObj = JSON.parse(message);

	var transactionLogElement = document.createElement("div");
	
	var result = transactionStatusObj.transactionStatus.status;
	
	if (result=="SUCCESS") {
		transactionLogElement.className = "alert alert-success";
	}
	
	if (result=="FAILURE") {
		transactionLogElement.className = "alert alert-danger";
	}
	
	if (result=="PENDING") {
		transactionLogElement.className = "alert alert-info";
	}
	
	transactionLogElement.role="alert";
	
	transactionLogElement.innerHTML = transactionStatusObj.transactionStatus.timestamp + " : " + transactionStatusObj.transactionStatus.message + " > " + transactionStatusObj.transactionStatus.status + ")";	
	content.insertBefore(transactionLogElement, content.firstChild);
	
}
