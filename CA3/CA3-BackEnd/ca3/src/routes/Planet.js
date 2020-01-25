import React, {Component} from 'react';
import "./Planets.css";


class Planet extends React.Component{
    constructor(){
        super();
        this.state = {
            planets: []
        }
        
    };


    componentDidMount() {
        fetch('https://swapi.co/api/planets/1' )
        .then(results => { return results.json();})
        .then(data => { let planets = data.results.map((planet) => {
            
            return(
               
               
               <div key={planet.results}>
                    <table id="table">
                    <tr><th>Name</th>       <th>Terrain</th>            <th>Population</th></tr>
                    <td>{planet.name}</td>  <td>{planet.terrain}</td>   <td>{planet.population}</td>
                    </table>                  
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
            </div>
        )
    }
}
export default Planet;