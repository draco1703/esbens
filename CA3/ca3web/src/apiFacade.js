import URL from './settings.js';

function handleHttpErrors(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
}

class ApiFacade {


    makeOptions(method, addToken, body) {
        var opts = {
            method: method,
            headers: {
                "Content-type": "application/json",
                'Accept': 'application/json',
            }
        }
        if (addToken && this.loggedIn()) {
            opts.headers["x-access-token"] = this.getToken();
        }
        if (body) {
            opts.body = JSON.stringify(body);
        }
        return opts;
    }

    setToken = (token) => {
        localStorage.setItem('jwtToken', token)
    }

    getToken = () => {
        return localStorage.getItem('jwtToken')
    }

    loggedIn = () => {
        const loggedIn = this.getToken() != null;
        return loggedIn;
    }

    logout = () => {
        localStorage.removeItem("jwtToken");
    }

    login = (user, pass) => {
        const options = this.makeOptions("POST", true, { username: user, password: pass });
        return fetch(URL + "/api/login", options, true)
            .then(handleHttpErrors)
            .then(res => { this.setToken(res.token) })
    }

    addNew = (user, pass) => {
        const options = this.makeOptions("POST", true, { username: user, password: pass });
        return fetch(URL + "/api/register", options, true)
            .then(handleHttpErrors)
            .then(res => { this.setToken(res.token) })
    }

    getRandom = () => {
        console.log("here")
        return fetch(URL + "/api/starwars/people/random")
            .then(handleHttpErrors)
            .then(res => { })
    }

    getSpecific = (id) => {
        const options = this.makeOptions("GET", true);
        return fetch(URL + "/api/starwars/people/" + id)
            .then(handleHttpErrors)
            .then(res => { })

    }
}


const facade = new ApiFacade();
export default facade;