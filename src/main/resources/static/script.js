let appName = "Coding Events";

var key ='WDc1b41Y0TAxeN1cHeBFWdvChIWFmiUVOaOaZHRT10EKDcqsHS';
var secret = '9JeuqGrfyuBtkB9SNSD78pLbupaPRv98uHrXWlAl';
//
let status = 'adoptable';
const petForm = document.querySelector('#pet-form');
petForm.addEventListener('submit', fetchAnimals);
let pets = [];
let map;

var geocoder
document.body.addEventListener("click", e =>{
    const target = e.target;
    if (target.classList.contains("animalPic")){
    const id =+ target.getAttribute("data-id");
    //GET https://api.petfinder.com/v2/animals/{id}
    const pet = pets.find(p=>p.id === id);
    console.log(pet);
    const address = `${pet.contact.address.address1}
            ${pet.contact.address.address2 ? pet.contact.address.address2 : ''}
            ${pet.contact.address.city}, ${pet.contact.address.state}, ${pet.contact.address.postcode}`;
            codeAddress(address);
    }
})

async function changeMap() {
    zip = document.getElementById('zip').value;
    const googleZipCodeURL = `https://maps.googleapis.com/maps/api/geocode/json?address=${zip}&key=AIzaSyDOuF8T4XovaUYJeaxH_gbBGXvzI67OH78`;
    const response = await fetch(googleZipCodeURL);
    const json = await response.json();
    console.log(json);
    var bounds = new google.maps.LatLngBounds();
    var northeast = new google.maps.LatLng(json.results[0].geometry.bounds.northeast.lat,json.results[0].geometry.bounds.northeast.lng);
    var southwest = new google.maps.LatLng(json.results[0].geometry.bounds.southwest.lat,json.results[0].geometry.bounds.southwest.lng);
    bounds.extend(northeast);
    bounds.extend(southwest);
    map.fitBounds(bounds);
    document.getElementById("map").style.visibility = "visible";
}

  function codeAddress(address) {
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == 'OK') {
        map.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
      } else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
  }


async function fetchAnimals() {
  let a = document.getElementById("animal");
  const animal = document.getElementById("animal").value;
  zip = document.getElementById('zip').value;
  await changeMap();

  const resp = await fetch('https://api.petfinder.com/v2/oauth2/token', {
	  method: 'POST',
	  body: 'grant_type=client_credentials&client_id=' + key + '&client_secret=' + secret,
	  headers: {
		'Content-Type': 'application/x-www-form-urlencoded'
	  }
  });
  const data = await resp.json();
  const petResponse = await fetch('https://api.petfinder.com/v2/animals?type=' + animal + '&location=' + zip + '&status=' + status, {
  		  headers: {
  			  'Authorization': data.token_type + ' ' + data.access_token,
  			  'Content-Type': 'application/x-www-form-urlencoded'
  		  }
  	  });
  	  const petData = await petResponse.json();
  	  displayPets(petData);
  	  console.log(petData);
  	  pets = petData.animals;
}


function displayPets(responseJson) {
  let html = '';
  for (let i=0; i<responseJson.animals.length; i++) {
  // for each loop?
    const {photos, contact, url, id, name} = responseJson.animals[i];
    const hasContactInfo = (contact.email || contact.phone) && contact.address.address1;
    const isValid = photos.length>0 && hasContactInfo;
    if (isValid) {
      html += `<a href="pet-details/?=${id}"><img src='${photos[0].medium}'></a>`;
      if(name) {
        html += `<a href="pet-details/?=${id}"><p style="text-decoration: underline;">${name}</p></a>`;
      }
      if(contact.email) {

        html += `<p>Email: ${contact.email}</p>`;
      }
      if(contact.phone) {
        html += `<p>Phone: ${contact.phone}</p>`;
      }
      if(contact.address) {
      // null coalessing
        html += `<p style="border-bottom: 2px dotted #b276c1; padding-bottom: 10px;">
        Location: ${contact.address.address1}
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

function initMap() {
  geocoder = new google.maps.Geocoder();
  const uluru = { lat: -25.344, lng: 131.031 };
  map = new google.maps.Map(document.getElementById("map"), {
    zoom: 4,
    center: uluru,
  });
}