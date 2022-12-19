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
  // for each loop?
    const {photos, contact, url, id} = responseJson.animals[i];
    const hasContactInfo = (contact.email || contact.phone) && contact.address.address1;
    const isValid = photos.length>0 && hasContactInfo;
    if (isValid) {
      html += `<a href="pet-details/?=${id}"><img src='${photos[0].small}'></a>`;
//  destructure or javascript destructure
      if(contact.email) {
        html += `<p>${contact.email}</p>`;
      }
      if(contact.phone) {
        html += `<p>${contact.phone}</p>`;
      }
      if(contact.address) {
      // null coalessing
        html += `<p>
        ${contact.address.address1}
        ${contact.address.address2 ? contact.address.address2 : ''}<br>
        ${contact.address.city}, ${contact.address.state}, ${contact.address.postcode}
        </p>`;
      }
      //google maps api?
      //{address1: "237 Centerville Rd Ste7", address2: null, city: "Lancaster", state: "PA",…}

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