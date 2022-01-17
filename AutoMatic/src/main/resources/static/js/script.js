window.addEventListener("load", function(e) {
	console.log("document loaded");
	init();
});

function init() {
	document.userForm.userSearch.addEventListener("click", function(e) {
		e.preventDefault();
		var userId = document.userForm.userId.value;
		if (!isNaN(userId) && userId > 0) {
			getUser(userId);
		}
	});

	document.newUserForm.createUser.addEventListener("click", createUser);

	document.userForm.deleteUser.addEventListener("click", function(e) {
		e.preventDefault();
		var userId = document.userForm.userId.value;
		if (!isNaN(userId) && userId > 0) {
			deleteUser(userId);
		}
	});

	document.newUserForm.updateUser.addEventListener("click", function(e) {
		e.preventDefault();
		var userId = document.newUserForm.id.value;
		if (!isNaN(userId) && userId > 0) {
			updateUser(userId);
		}
	});
	
	document.vehicleForm.vehicleSearch.addEventListener("click", function(e) {
		e.preventDefault();
		var vehicleId = document.vehicleForm.vehicleId.value;
		if (!isNaN(vehicleId) && vehicleId > 0) {
			getVehicle(vehicleId);
		}
		console.log("here");
	});
}

function displayError(msg) {
	var dataDiv = document.getElementById("userData");
	dataDiv.textContent = msg;
}

function getUser(userId) {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "api/users/" + userId);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				displayUser(JSON.parse(xhr.responseText));
			} else if (xhr.status === 404) {
				displayError("User not found.")
			} else {
				displayError("Error retrieving user: " + xhr.status);
			}
		}
	}
	xhr.send();
}

function getVehicles(userId) {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", `api/users/${userId}/vehicles`);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				displayVehicles(JSON.parse(xhr.responseText));
			}
		}
	}
	xhr.send();
}

function displayUser(user) {
	var dataDiv = document.getElementById("userData");
	dataDiv.textContent = "";

	let h3 = document.createElement("h3");
	h3.textContent = "User Info: ";
	dataDiv.appendChild(h3);

	let details = document.createElement("ul");
	dataDiv.appendChild(details);

	let email = document.createElement("li");
	email.textContent = "Email: " + user.email;
	dataDiv.appendChild(email);

	let fName = document.createElement("li");
	fName.textContent = "First Name: " + user.firstName;
	dataDiv.appendChild(fName);

	let lName = document.createElement("li");
	lName.textContent = "Last Name: " + user.lastName;
	dataDiv.appendChild(lName);

	getVehicles(user.id);

}

function displayVehicles(vehicles) {
	var vehicleDiv = document.getElementById("vehicleData");
	vehicleDiv.textContent = "";

	if (vehicles.length > 0) {
		let h3 = document.createElement("h3");
		h3.textContent = "Vehicles: ";
		vehicleDiv.appendChild(h3);

		let vehicleUl = document.createElement("ul");
		vehicleDiv.appendChild(vehicleUl);
		for (let vehicle of vehicles) {
			let li = document.createElement("li");
			li.textContent = vehicle.year + " " + vehicle.make + ", " + vehicle.model;
			vehicleUl.appendChild(li);
		}
	} 
}

function createUser(e) {
	e.preventDefault();
	var user = {
		email: document.newUserForm.email.value,
		password: document.newUserForm.password.value,
		firstName: document.newUserForm.fName.value,
		lastName: document.newUserForm.lName.value
	}
	postUser(user);
}

function postUser(user) {
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "api/users");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200 || xhr.status === 201) {
				displayUser(user);
			} else if (xhr.status === 404) {
				displayError("Post request failed.")
			} else {
				displayError("Error creating user: " + xhr.status);
			}
		}
	}
	xhr.setRequestHeader("content-type", "application/json");
	xhr.send(JSON.stringify(user));
}

function deleteUser(userId) {
	let xhr = new XMLHttpRequest();
	xhr.open("DELETE", "api/users/" + userId);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 204) {
				displayError("User deleted");
			} else if (xhr.status === 404) {
				displayError("User not found.")
			} else {
				displayError("Error retrieving user: " + xhr.status);
			}
		}
	}
	xhr.send();
}

function updateUser(userId) {
	var user = {
		email: document.newUserForm.email.value,
		password: document.newUserForm.password.value,
		firstName: document.newUserForm.fName.value,
		lastName: document.newUserForm.lName.value
	}
	let xhr = new XMLHttpRequest();
	xhr.open("PUT", "api/users/" + userId);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				getUser(userId);
			} else if (xhr.status === 404) {
				displayError("User not found.")
			} else {
				displayError("Error retrieving user: " + xhr.status);
			}
		}
	}
	xhr.setRequestHeader("content-type", "application/json");
	xhr.send(JSON.stringify(user));
}

function getVehicle(vehicleId) {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "api/vehicles/" + vehicleId);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				vehicleDisplay(JSON.parse(xhr.responseText));
			} else if (xhr.status === 404) {
				displayError("Vehicle not found.")
			} else {
				displayError("Error retrieving vehicle: " + xhr.status);
			}
		}
	}
	xhr.send();
}

function getServices(vehicleId) {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", `api/vehicles/${vehicleId}/services`);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				serviceDisplay(JSON.parse(xhr.responseText));
			}
		}
	}
	xhr.send();
}

function vehicleDisplay(vehicle) {
	var vehicleDiv = document.getElementById("vehicleData");
	vehicleDiv.textContent = "";
	
	let h3 = document.createElement("h3");
	h3.textContent = "Vehicle Info: ";
	vehicleDiv.appendChild(h3);
	
	let details = document.createElement("ul");
	vehicleDiv.appendChild(details);

	let vin = document.createElement("li");
	vin.textContent = "VIN: " + vehicle.vin;
	vehicleDiv.appendChild(vin);

	let make = document.createElement("li");
	make.textContent = "Make: " + vehicle.make;
	vehicleDiv.appendChild(make);

	let model = document.createElement("li");
	model.textContent = "Model: " + vehicle.model;
	vehicleDiv.appendChild(model);
	
	let year = document.createElement("li");
	year.textContent = "Year: " + vehicle.year;
	vehicleDiv.appendChild(year);
	
	let color = document.createElement("li");
	color.textContent = "Color: " + vehicle.color;
	vehicleDiv.appendChild(color);

	getServices(vehicle.id);
}

function serviceDisplay(services) {
	var serviceDiv = document.getElementById("serviceData");
	serviceDiv.textContent = "";

	if (services.length > 0) {
		let h3 = document.createElement("h3");
		h3.textContent = "Services: ";
		serviceDiv.appendChild(h3);

		let servicesUl = document.createElement("ul");
		serviceDiv.appendChild(servicesUl);
		for (let service of services) {
			let li = document.createElement("li");
			li.textContent = "Service: " + service.type + ", Milage:" + service.odometer + ", Cost: $" + service.cost;
			servicesUl.appendChild(li);
		}
	} 
}