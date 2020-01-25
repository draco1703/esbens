const tblbody = document.getElementById("tblbody");
const AMT = document.getElementById("amount");
const STID = document.getElementById("starting");

function doSubmit() {
                console.log("HERERERERERE");

    let promise = fetch("/SPDataGen/api/data/" + AMT.value + "/" + STID.value, {method: 'get'});

    promise
            .then(function (response) {
                return response.text();
            })
            .then(function (text) {
                console.log("----------HERE-------------");
                //console.log(text);
                ppl = JSON.parse(text);
                console.log(ppl);
                if (ppl.length > 1) {
//                    tblbody.innerHTML = "<tr><td>" + ppl[0].fname + "</td><td>" + ppl[0].lname + "</td><td>" + ppl[0].id + "</td><td>" + ppl[0].age + "</td></tr>";
                    for (var i = 0; i < ppl.length; i++) {
                        tblbody.innerHTML += "<tr><td>" + ppl[i].fname + "</td><td>" + ppl[i].lname + "</td><td>" + ppl[i].id + "</td><td>" + ppl[i].age + "</td></tr>";
                    }
                } else {
                    tblbody.innerHTML = "<tr><td>" + ppl.fname + "</td><td>" + ppl.lname + "</td><td>" + ppl.id + "</td><td>" + ppl.age + "</td></tr>";
                }

            });
}