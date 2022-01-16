window.addEventListener("load", function (e) {
	console.log("document loaded");
	init();
});

function init() {
	document.userForm.userSearch.addEventListener("click", getUser);
	document.vehicleForm.vehicldSearch.addEventListener("click", getVehicle);
}

function displayError(msg) {
	var dataDiv = document.getElementById("userData");
	dataDiv.textContent = msg;
}

function getUser(e) {
	e.preventDefault();
	let xhr = XMLHttpRequest();
	xhr.open("GET", "api/users");
	xhr.onreadystatuschange = function () {
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

function displayUser(user) {
	var dataDiv = document.getElementById("userData");
	dataDiv.textContent = "";

	let h3 = document.createElement("h3");
	h1.textContent = "User Info: ";
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

	let bornOn = document.createElement("li");
	bornOn.textContent = "User Since: " + user.created;
	dataDiv.appendChild(bornOn);

	let updatedOn = document.createElement("li");
	updatedOn.textContent = "Profile Last Updated: " + user.updated;

	getVehicles(user.id);

}

function getVehicles(userId) {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", `api/user/${userId}/vehicles`);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				displayVehicles(JSON.parse(xhr.responseText));
			}
		}
	}
	xhr.send();
}

function displayVehicles(vehicles) {
	var vehicleDiv = document.getElementById("vehicleData");
	vehicleDiv.textContent = "";

	let h3 = document.createElement("h3");
	h3.textContent = "Vehicles: ";
	vehicleDiv.appendChild(h3);

	if (vehicles.length > 0) {
		let vehicleUl = document.createElement("ul");
		vehicleDiv.appendChild(vehicleUl);
		for (let vehicle of vehicles) {
			let li = document.createElement("li");
			li.textContent = vehicle.year + " " + vehicle.make + ", " + vehicle.model;
			vehicleUl.appendChild(li);
		}
	}
}