window.onload = init;
var socket = new WebSocket("ws://localhost:8080/Web-Ex-WebSockets-1.0-SNAPSHOT/actions");
socket.onmessage = onMessage;

function onMessage(event) {
	var device = JSON.parse(event.data);
	if (device.action === "add") {
		printDeviceElement(device);
	}
	if (device.action === "remove") {
		document.getElementById(device.id).remove();
		// device.parentNode.removeChild(device);
	}
	if (device.action === "toggle") {
		var node = document.getElementById(device.id);
		var statusText = node.children[3];
		if (device.status === "On") {
			statusText.innerHTML = "Status: " + device.status
					+ " (<a href=\"#\" OnClick=toggleDevice(" + device.id
					+ ")>Turn off</a>)";
		} else if (device.status === "Off") {
			statusText.innerHTML = "Status: " + device.status
					+ " (<a href=\"#\" OnClick=toggleDevice(" + device.id
					+ ")>Turn on</a>)";
		}
	}
}

function addDevice(name, type, shared, autooffsec, description) {
	var DeviceAction = {
		action : "add",
		name : name,
		type : type,
		shared : shared,
		autooffsec : autooffsec,
		description : description
	};
	socket.send(JSON.stringify(DeviceAction));
}

function removeDevice(element) {
	var id = element;
	var DeviceAction = {
		action : "remove",
		id : id
	};
	socket.send(JSON.stringify(DeviceAction));
}

function toggleDevice(element) {
	var id = element;
	var DeviceAction = {
		action : "toggle",
		id : id
	};
	socket.send(JSON.stringify(DeviceAction));
}

function printDeviceElement(device) {
	var content = document.getElementById("content");

	var deviceDiv = document.createElement("div");
	deviceDiv.setAttribute("id", device.id);
	deviceDiv.setAttribute("class", "device " + device.type);
	content.appendChild(deviceDiv);

	var deviceName = document.createElement("span");
	deviceName.setAttribute("class", "deviceName");
	deviceName.innerHTML = device.name;
	deviceDiv.appendChild(deviceName);

	var deviceType = document.createElement("span");
	deviceType.innerHTML = "<b>Type:</b> " + device.type;
	deviceDiv.appendChild(deviceType);

	var deviceShared = document.createElement("span");
    deviceShared.innerHTML = "<b>"+device.shared+"</b> ";
	deviceDiv.appendChild(deviceShared);

	var deviceStatus = document.createElement("span");
	if (device.status === "On") {
		deviceStatus.innerHTML = "<b>Status:</b> " + device.status
				+ " (<a href=\"#\" OnClick=toggleDevice(" + device.id
				+ ")>Turn off</a>)";
	} else {
		deviceStatus.innerHTML = "<b>Status:</b> " + device.status
				+ " (<a href=\"#\" OnClick=toggleDevice(" + device.id
				+ ")>Turn on</a>)";
		// deviceDiv.setAttribute("class", "device off");
	}
	deviceDiv.appendChild(deviceStatus);

	if (device.autooffsec > 0) {
    	var deviceAutoOffSec = document.createElement("span");
    	deviceAutoOffSec.innerHTML = "<b>Automatic off</b> in " + device.autooffsec + " sec";
    	deviceDiv.appendChild(deviceAutoOffSec);
	}
	
	var deviceDescription = document.createElement("span");
	deviceDescription.innerHTML = "<b>Comments:</b> " + device.description;
	deviceDiv.appendChild(deviceDescription);

	var removeDevice = document.createElement("span");
	removeDevice.setAttribute("class", "removeDevice");
	removeDevice.innerHTML = "<a href=\"#\" OnClick=removeDevice(" + device.id
			+ ")>Remove device</a>";
	deviceDiv.appendChild(removeDevice);
}

function showForm() {
	document.getElementById("addDeviceForm").style.display = '';
}

function hideForm() {
	document.getElementById("addDeviceForm").style.display = "none";
}

function formSubmit() {
	var form = document.getElementById("addDeviceForm");
	var name = form.elements["device_name"].value;
	var type = form.elements["device_type"].value;
	var shared = form.elements["device_shared"].value;
	var autooffsec = form.elements["device_autooffsec"].value;
	var description = form.elements["device_description"].value;
	hideForm();
	document.getElementById("addDeviceForm").reset();
	addDevice(name, type, shared, autooffsec, description);
}

function init() {
	hideForm();
}
