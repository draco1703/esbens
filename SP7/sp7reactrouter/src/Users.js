import React, { Component } from 'react';
import { BrowserRouter as Link } from "react-router-dom"
import data from "./data/data.json"

class Users extends Component {
    constructor(props) {
        super(props);
        this.state = { people: [] };
    }

    componentWillMount() {
        let people = data.users;
        this.setState({ people });
    }

    render() {
        return (
            <div>
                <CreateTable people={this.state.people} />
            </div>
        );
    }

}

const CreateTable = ({ people }) => {
    const user = people;
    console.log(user)
    return (
        <div>
            <table className="table">
                <thead>
                    <tr>
                        <th>Name:</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    {user.map((index, i) =>
                        <tr key={index}>
                            <td><img src={user[i].picture.thumbnail} alt="" /></td>
                            <td>{user[i].first} {user[i].last}</td>
                            <td><Link to={`/details/${i}`}>Details</Link></td>
                        </tr>)}
                </tbody>
            </table>
        </div>
    );
}

export default Users;