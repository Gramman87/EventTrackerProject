window.addEventListener("load", function (e) {
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
}

function displayError(msg) {
	var dataDiv = document.getElementById("userData");
	dataDiv.textContent = msg;
}

function getUser(userId) {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "api/users/" + userId);
	xhr.onreadystatechange = function () {
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
	xhr.onreadystatechange = function () {
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
	xhr.onreadystatechange = function () {
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