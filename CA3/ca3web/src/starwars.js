import React, { Component } from 'react';
import URL from './settings.js';
import { BrowserRouter as Router, Route, Switch, NavLink } from "react-router-dom";
import facade from "./apiFacade";


class StarwarsTable extends Component {
    constructor() {
        super();
        this.state = {
            person: ""
        }
    }

    componentDidMount() {
        fetch(URL + "/api/starwars/people/random")
            .then((response) => {
                return response.json();
            })
            .then(responseJson =>
                this.setState({
                    person: responseJson
                })
            );
    }

    getRandom = () => {
        this.componentDidMount();
    }
       

    render() {
        return (
            <div>
                <br></br>
                <button onClick={this.getRandom}>Random</button>

                <br></br>

                <Display person={this.state.person} />
            </div>
        )
    }
}

const Display = ({ person }) => {
    if (person === 'undefined') {
        return (
            <div></div>
        );
    }
    else {
        return (<div>
            <form>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Hair Colour</th>
                            <th>Skin Colour</th>
                            <th>Eye Colour</th>
                            <th>Birth Year</th>
                            <th>Height</th>
                            <th>Mass</th>
                            <th>Gender</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{person.name}</td>
                            <td>{person.hair_color}</td>
                            <td>{person.skin_color}</td>
                            <td>{person.eye_color}</td>
                            <td>{person.height}</td>
                            <td>{person.mass}</td>
                            <td>{person.gender}</td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>);
    }

}

export default StarwarsTable;