let appName = "Coding Events";

var key ='WDc1b41Y0TAxeN1cHeBFWdvChIWFmiUVOaOaZHRT10EKDcqsHS'
var secret =  'MSMgDjrpPTWEb0n1NGICQQNn8uBUqoea8iJGqT1o'

// Call details
var status = 'adoptable';
const petForm = document.querySelector('#pet-form');
petForm.addEventListener('submit', fetchAnimals);

//Fetch Animals From API
function fetchAnimals() {
  var a = document.getElementById("animal");
  const animal = $('#animal').val();
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
  .catch(error => alert('Something went wrong with Petfinder API. Try again later.'))
}

function displayPets(responseJson) {
  let html = '';
  for (let i=0; i<responseJson.animals.length; i++) {
    const animal = responseJson.animals[i];
    if (animal.photos.length > 0) {
      html += `<img src=${responseJson.animals[i].photos[0].small} />`
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
  $('#petResults').empty();
  $('#petResults').html(html);
}
