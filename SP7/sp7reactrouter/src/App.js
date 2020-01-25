import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom"
import Users from './Users';
import details from './Details';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Router>
          <div>
            <h1>People </h1>
            <Switch>
              <Route exact={true} path="/" component={welcomePage} />
              <Route exact={true} path="/all" component={Users} />
              <Route path="/details/:index" component={details} />
            </Switch>
          </div>
        </Router>

      </div>
    );
  }
}

const welcomePage = () => {
  return (
    <div>
      <p>Welcome to our glorious site!</p>
      <p>we hope you have a wonderful time here</p>
      <ol>
        <Link to="/all">All users</Link>
      </ol>
    </div>);
}

export default App;
