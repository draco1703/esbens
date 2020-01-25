
var lastcountry;
var targetid;
document.getElementById("svg2").onclick = changeTarget;

function changeTarget(evt) {
    if (lastcountry != undefined) {
        document.getElementById(lastcountry).style.fill = "#c0c0c0";
    }
    targetid = evt.target.id;
    evt.target.style.fill = "red";
    lastcountry = targetid;
    fetch("http://restcountries.eu/rest/v1/alpha?codes=" + targetid)
        .then(response => response.json())
        .then(data => toView(data));
}

function toView(data) {
    document.getElementById("cinfo").innerHTML = "Country: " + data[0].name + "<br>Population: " + data[0].population + "<br>Bordering Countries: " + data[0].borders + "<br>Capital: " + data[0].capital + "<br>Area: " + data[0].area + "km²<br>";
}