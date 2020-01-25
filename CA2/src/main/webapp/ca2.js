/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const URL = "/CA2/api/person/"; //api root url

var hobbies;
var phone;

document.getElementById("CIButton").addEventListener("click", CityInfoClick);
document.getElementById("allButton").addEventListener("click", AllClickComplete);
document.getElementById("allContactButton").addEventListener("click", AllClickContact);
document.getElementById("getUserID").addEventListener("click", SpecificClickComplete);
document.getElementById("getContactID").addEventListener("click", SpecificClickContact);
document.getElementById("deleteButton").addEventListener("click", DeleteClick);
document.getElementById("addperson").addEventListener("click", AddClick);

function SpecificClickComplete() {
    var conf = {method: 'get'};
    var promise = fetch(URL + document.getElementById('PID').value, conf);
    promise.then(function (response) {
        return response.text();
    }).then(function (text) {
        data = JSON.parse(text);
        console.log(data)
        if (typeof data.phones[0] === 'undefined') {
            phone = "none found";
        } else {
            phone = data.phones[0].number;
        }
        if (typeof data.hobbies[0] === 'undefined') {
            hobbies = '<div> HobbyID: none found</div><div> Hobby Name: none found</div><div> Hobby Description: none found</div>';
        } else {
            hobbies = '<div> HobbyID: ' + data.hobbies[0].id + '</div><div> Hobby Name: ' + data.hobbies[0].name + '</div><div> Hobby Description: ' + data.hobbies[0].discription + '</div>';
        }
        document.getElementById('container').innerHTML = ('<div> Id:' + data.id + '</div>')
        document.getElementById('container').innerHTML += ('<div> mail:' + data.email + '</div>')
        document.getElementById('container').innerHTML += ('<div> Name: ' + data.fName + ' ' + data.lName + '</div>')
        document.getElementById('container').innerHTML += ('<div> Phone:' + phone + '</div>')
        document.getElementById('container').innerHTML += ('<p></p><b>Hobby</b>')
        document.getElementById('container').innerHTML += (hobbies)
    });
}

function CityInfoClick() {
    var conf = {method: 'get'};
    var promise = fetch(URL + "city/all", conf);
    promise.then(function (response) {
        return response.text();
    }).then(function (text) {
        data = JSON.parse(text);
        console.log(data)

        document.getElementById('container').innerHTML = ('<table id = Table><th>ID</th><th>ZIP</th><th>City Name</th>')
        for (var i = 0; i < data.length; i++) {
            document.getElementById('Table').innerHTML += ('<tr><td> ' + data[i].id + '</td><td> ' + data[i].zip + '</td><td> ' + data[i].city + '</td></tr>')
        }
    });
}

function SpecificClickContact() {
    var conf = {method: 'get'};
    var promise = fetch(URL + "contact/" + document.getElementById('ContactPID').value, conf);
    promise.then(function (response) {
        return response.text();
    }).then(function (text) {
        data = JSON.parse(text);
        console.log(data)

        if (typeof data.phones[0] === 'undefined') {
            phone = "none found";
        } else {
            phone = data.phones[0].number;
        }

        document.getElementById('container').innerHTML = ('<div> Id:' + data.id + '</div>')
        document.getElementById('container').innerHTML += ('<div> mail:' + data.email + '</div>')
        document.getElementById('container').innerHTML += ('<div> Name: ' + data.fName + ' ' + data.lName + '</div>')
        document.getElementById('container').innerHTML += ('<div> Phone:' + phone + '</div>')
    });
}

function AllClickComplete() {
    var conf = {method: 'get'};
    var promise = fetch(URL + "all", conf);
    promise.then(function (response) {
        return response.text();
    }).then(function (text) {
        data = JSON.parse(text);
        console.log(data)

        document.getElementById('container').innerHTML = ('<table id = Table><th>ID</th><th>Mail</th><th>Name</th><th>Phone</th><th>HobbyID</th><th>Hobby Name</th><th>Hobby Description</th>')
        for (var i = 0; i < data.length; i++) {
            if (typeof data[i].phones[0] === 'undefined') {
                phone = "none found";
            } else {
                phone = data[i].phones[0].number;
            }
            if (typeof data[i].hobbies[0] === 'undefined') {
                hobbies = '<td> None found </td><td> None found </td><td> None found </td>';
            } else {
                hobbies = '<td> ' + data[i].hobby[0].id + '</td><td> ' + data[i].hobby[0].name + '</td><td> ' + data[i].hobby[0].discription + '</td>';
            }

            document.getElementById('Table').innerHTML += ('<tr><td> ' + data[i].id + '</td><td> ' + data[i].email + '</td><td> ' + data[i].fName + ' ' + data[i].lName + '</td><td> ' + phone + '</td></tr>')
        }
    });
}

function AllClickContact() {
    var conf = {method: 'get'};
    var promise = fetch(URL + "contact/all", conf);
    promise.then(function (response) {
        return response.text();
    }).then(function (text) {
        data = JSON.parse(text);
        console.log(data)

        document.getElementById('container').innerHTML = ('<table id = userTable><th>ID</th><th>Mail</th><th>Name</th><th>Phone</th>')
        for (var i = 0; i < data.length; i++) {
            if (typeof data[i].phones[0] === 'undefined') {
                phone = "none found";
            } else {
                phone = data[i].phones[0].number;
            }
            document.getElementById('userTable').innerHTML += ('<tr><td> ' + data[i].id + '</td><td> ' + data[i].email + '</td><td> ' + data[i].fName + ' ' + data[i].lName + '</td><td> ' + phone + '</td></tr>')
        }
    });
}

function DeleteClick() {
    var conf = {method: 'DELETE'};
    var promise = fetch(URL + document.getElementById('deletePID').value, conf);
    promise.then(response => handleHttpErrors(response))
            .then(function (data) {
                document.getElementById('container').innerHTML = '<p>User deleted successfully</p>';
            })
            .catch(err => {
                if (err.httpError) {
                    err.fullError.then(errJson => document.getElementById('container').innerHTML = '<p>' + errJson.detailMessage + '</p>');
                }
            });
}

function AddClick() {
    let options = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            fName: document.getElementById('firstname').value,
            lName: document.getElementById('lastname').value,
            email: document.getElementById('email').value,
            street: document.getElementById('streetname').value,
            additionalInfo: document.getElementById('housenumber').value,
            zip: document.getElementById('zip').value
        })
    };
    var promise = fetch(URL, options);
    promise.then(response => handleHttpErrors(response))
            .then(function (data) {
                document.getElementById('container').innerHTML = '<p>User added successfully</p>';
            })
            .catch(err => {
                if (err.httpError) {
                    err.fullError.then(errJson => document.getElementById('container').innerHTML = '<p>' + errJson.detailMessage + '</p>');
                }
            });
}


function handleHttpErrors(response) {
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject({httpError: response.status, fullError: response.json()});
    }
}


