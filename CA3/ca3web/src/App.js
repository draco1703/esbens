import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch, NavLink } from "react-router-dom";
import SWTable from './starwars';
import facade from "./apiFacade";



class App extends Component {
  constructor(props) {
    super(props);
    this.state = { loggedIn: false, role: "" }
  }

  logout = () => {
    facade.logout();
    this.setState({ loggedIn: false });
  }

  login = (user, pass) => {
    facade.login(user, pass)
      .then(res => this.setState({ loggedIn: true }));
  }



  render() {
    return (
      <Router>
        <div>
          {!this.state.loggedIn ? (<LogIn login={this.login} />) :
            (<div>
              <LoggedIn logout={this.logout} />
            </div>)}
        </div>
      </Router>
    )
  }
}

class LogIn extends Component {
  constructor(props) {
    super(props);
    this.state = { username: "", password: "", newuser: "", newpw: "", newpw2: "", msg: "" }
  }

  login = (evt) => {
    evt.preventDefault();
    this.props.login(this.state.username, this.state.password);
  }

  onChange = (evt) => {
    this.setState({ [evt.target.id]: evt.target.value, msg: "" })
  }

  render() {
    return (
      <div>
        <Header user={this.state.dataFromServer} logout={this.props.logout} />
        <br />
        <form onSubmit={this.login} onChange={this.onChange} >
            <p>Login</p>
            <input placeholder="User Name" id="username" />
            <input placeholder="Password" id="password" />
            <button>Login</button>
        </form>
        <br></br>
        <p>{this.state.msg}</p>
      </div>
    )
  }
}

class LoggedIn extends Component {
  constructor(props) {
    super(props);
    this.state = { dataFromServer: "Fetching!!" };
  }

  componentDidMount() {
  }
  render() {
    return (
      <div>
        <Header logout={this.props.logout} />
      </div>
    )
  }
}

function Header(props) {
  let role = "";
  if (localStorage.jwtToken) {
    let jwt = localStorage.jwtToken;
    let jwtData = jwt.split('.')[1]
    let decodedJwtJsonData = window.atob(jwtData)
    let decodedJwtData = JSON.parse(decodedJwtJsonData)
    role = decodedJwtData.roles
  }
  if (role === "user") {
    return (
      <Router>
        <div>
            <NavLink exact activeClassName="active" to="/">Home</NavLink>
            <br></br>
            <NavLink activeClassName="active" to="/logout" onClick={props.logout}>Logout </NavLink>
            <br></br>
            <NavLink exact activeClassName="active" to="/starwars">starwars </NavLink>
          <Switch>
            <Route exact path="/" component={welcome} />
            <Route exact path="/starwars" component={SWTable} />
          </Switch>
        </div>
      </ Router>
    )
  }
  else if (role === "admin") {
    return (
      <Router>
        <div>
            <NavLink exact activeClassName="active" to="/">Home </NavLink>
            <br></br>
            <NavLink activeClassName="active" to="/logout" onClick={props.logout}>Logout </NavLink>
            <br></br>
            <NavLink exact activeClassName="active" to="/starwars">starwars </NavLink>
          <Switch>
            <Route exact path="/" component={welcome} />
            <Route exact path="/starwars" component={SWTable} />
          </Switch>
        </div>
      </ Router>
    )
  }
  else {
    return (
      <Router>
        <div>
            Not logged in
          <Switch>
            <Route exact path="/" component={welcome} />
          </Switch>
        </div>
      </ Router>
    )

  }
}

const welcome = () => {
  return (
    <div>
      Welcome!
      You are able to login, and look up random people from the star wars universe. (Or specific if you have the id corresponding their placement in the api from which we get our data)
      </div>
  );
}



export default App;
