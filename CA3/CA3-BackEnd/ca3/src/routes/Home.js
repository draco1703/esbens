import React from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import Planets from "./Planets";

const routes = [
    {
      path: "/",
      exact: true,
      main: () => <h2>Home</h2>
    },
    {
      path: "/Planets",
      main: () => <Route component={Planets}/>
    },
    {
      path: "/Spaceships",
      main: () => <h2>Spaceships</h2>
    },
    {
        path: "/Vehicles",
        main: () => <h2>Vehicles</h2>
      },
      {
        path: "/People",
        main: () => <h2>People</h2>
      },
      {
        path: "/Species",
        main: () => <h2>Species</h2>
      }
  ];
  
  function SidebarExample() {
    return (
      <Router>
        <div style={{ display: "flex" }}>
          <div
            style={{
              padding: "10px",
              width: "7%",
              background: "#f0f0f0"
            }}
          >
            <ul style={{ listStyleType: "none", padding: 0 }}>
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/Planets">Planets</Link>
              </li>
              <li>
                <Link to="/Spaceships">Spaceships</Link>
              </li>
              <li>
                <Link to="/Vehicles">Vehicles</Link>
              </li>
              <li>
                <Link to="/People">People</Link>
              </li>
              <li>
                <Link to="/Species">Species</Link>
              </li>
            </ul>
  
            {routes.map((route, index) => (
              <Route
                key={index}
                path={route.path}
                exact={route.exact}
                component={route.sidebar}
              />
            ))}
          </div>
  
          <div style={{ flex: 1, padding: "10px" }}>
            {routes.map((route, index) => (
              <Route
                key={index}
                path={route.path}
                exact={route.exact}
                component={route.main}
              />
            ))}
          </div>
        </div>
      </Router>
    );
  }
  

  export default SidebarExample;



