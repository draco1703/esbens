
const tblbody = document.getElementById("tblbody");
const url = "/SPPetHosp/api/pet/";

function doSubmitAll() {

    let promise = fetch(url, {method: 'get'});

    promise
            .then(function (response) {
                return response.text();
            })
            .then(function (text) {
                pets = JSON.parse(text);

                listPets(pets);

            });
}

function listPets(pets) {
    if (pets.length > 1) {
        tblbody.innerHTML = "<tr><td>" + pets[0].id + "</td><td>" + pets[0].name + "</td><td>" + pets[0].birth + "</td><td>" + pets[0].species + "</td><td>" + pets[0].owner.firstName + "</td><td>" + pets[0].owner.lastName + "</td></tr>";
        for (var i = 1; i < pets.length; i++) {
            tblbody.innerHTML += "<tr><td>" + pets[i].id + "</td><td>" + pets[i].name + "</td><td>" + pets[i].birth + "</td><td>" + pets[i].species + "</td><td>" + pets[i].owner.firstName + "</td><td>" + pets[i].owner.lastName + "</td></tr>";
        }
    } else {
        tblbody.innerHTML = "<tr><td>" + pets.id + "</td><td>" + pets.name + "</td><td>" + pets.birth + "</td><td>" + pets.species + "</td><td>" + pets.owner.firstName + "</td><td>" + pets.owner.lastName + "</td></tr>";
    }
}
