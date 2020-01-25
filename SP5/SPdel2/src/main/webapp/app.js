
const GEN = document.getElementById("gender");
const REG = document.getElementById("region");
const AMT = document.getElementById("amount");
var ppl;

function clear() {
    document.getElementById("tblbody").innerHTML = "";
    document.getElementById("sql").innerHTML = ""
}

function sqlify(pers) {
    return "('" + pers.name + "'," + "'" + pers.surname + "'," + "'" + pers.gender + "')";
}

function doSubmit() {
    clear();

    let genVal = "&gender=" + GEN.value;
    let regVal = "&region=" + REG.value;
    if (GEN.value === "both") {
        genVal = "";
    }
    if (REG.value === "All") {
        regVal = "";
    }

    let URL = "http://uinames.com/api/?amount=" + AMT.value + genVal + regVal;

    let promise = fetch(URL, { method: 'get' });

    promise
        .then(function (response) {
            if(response.status >= 400){
                alert('Error! You fucked up.');
            }
            return response.text();
        })
        .then(function (text) {
            ppl = JSON.parse(text);

            if (ppl.length > 1) {
                tblbody.innerHTML = "<tr><td>" + ppl[0].name + "</td><td>" + ppl[0].surname + "</td><td>" + ppl[0].gender + "</td></tr>";
                for (var i = 1; i < ppl.length; i++) {
                    tblbody.innerHTML += "<tr><td>" + ppl[i].name + "</td><td>" + ppl[i].surname + "</td><td>" + ppl[i].gender + "</td></tr>";
                }
            } else {
                tblbody.innerHTML = "<tr><td>" + ppl.name + "</td><td>" + ppl.surname + "</td><td>" + ppl.gender + "</td></tr>";
            }

        });
}

function getSQL() {
    if (ppl.length > 1) {
        sql.innerHTML = "INSERT INTO `names` (fname,lname,gender) VALUES " + ppl.map(sqlify) + ";";
    } else {
        sql.innerHTML = "INSERT INTO `names` (fname,lname,gender) VALUES " + sqlify(ppl) + ";";
    }
}
