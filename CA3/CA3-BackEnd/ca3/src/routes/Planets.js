import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import "./Planets.css";
import Planet from './Planet';

var i = 0;

class Planets extends React.Component{
    constructor(){
        super();
        this.state = {
            planets: []
        }
        
    };


    componentDidMount() {
        i++;
        fetch('https://swapi.co/api/planets/?page=' +i )
        .then(results => { return results.json();})
        .then(data => { let planets = data.results.map((planet) => {
            
            return(
               
               
               <div key={planet.results}> 
                    <h2>{planet.name}</h2>
                    <Route component={Planet}/>
                                
                </div>
            )
        })
    this.setState({planets: planets});
    console.log("state", this.state.planets);   
    })
   
    }
    render(){
        return(
            <div className="container2">
                <div className="container1">
                    {this.state.planets}

                </div>
                 <button onClick={(e) => this.componentDidMount()}>
                back
                </button>
                <button onClick={(e) => this.componentDidMount()}>
                next
                </button>
            </div>
        )
    }
}
export default Planets;