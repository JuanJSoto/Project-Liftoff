let appName = "Coding Events";

var key ='WDc1b41Y0TAxeN1cHeBFWdvChIWFmiUVOaOaZHRT10EKDcqsHS';
var secret = '9JeuqGrfyuBtkB9SNSD78pLbupaPRv98uHrXWlAl';
//
let status = 'adoptable';
const petForm = document.querySelector('#pet-form');
petForm.addEventListener('submit', fetchAnimals);

function fetchAnimals() {
  let a = document.getElementById("animal");
  const animal = document.getElementById("animal").value;
  zip = document.getElementById('zip').value;


  fetch('https://api.petfinder.com/v2/oauth2/token', {
	  method: 'POST',
	  body: 'grant_type=client_credentials&client_id=' + key + '&client_secret=' + secret,
	  headers: {
		'Content-Type': 'application/x-www-form-urlencoded'
	  }
  }).then(function (resp) {
	  return resp.json();
  }).then(function (data) {
	  return fetch('https://api.petfinder.com/v2/animals?type=' + animal + '&location=' + zip + '&status=' + status, {
		  headers: {
			  'Authorization': data.token_type + ' ' + data.access_token,
			  'Content-Type': 'application/x-www-form-urlencoded'
		  }
	  });
  }).then(response => {
    if(response.ok) {
      return response.json();
    }
  throw new Error(response.statusText);
  })
  .then(responseJson => displayPets(responseJson))
}

function displayPets(responseJson) {
  let html = '';
  for (let i=0; i<responseJson.animals.length; i++) {
    const animal = responseJson.animals[i];
    if (animal.photos.length > 0) {
      html += `<img src='${responseJson.animals[i].photos[0].small}' />`;
    }

    if(animal.contact.email) {
      html += `<br/><p>${animal.contact.email}</p>`;
    }

    if(animal.contact.phone) {
      html += `<br/><p>${animal.contact.phone}</p>`;
    }

    if(animal.contact.address1) {
      html += `<br/><p>${animal.contact.address1}</p>`;
    }
  }
  document.getElementById("petResults").innerHTML = html;
  console.log('#petResults');
}

function watchForm() {
    let button = document.getElementById("submitButton");
    button.addEventListener("click", ()=> {
    event.preventDefault();
    fetchAnimals();
  });
}

document.addEventListener("DOMContentLoaded", () => {
  console.log('App loaded! Waiting for submit!');
  watchForm();
});