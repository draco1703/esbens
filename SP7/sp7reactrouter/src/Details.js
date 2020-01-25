import React, { Component } from 'react';
import data from './data/data.json';
import { Link } from 'react-router-dom';



class details extends Component {

    constructor(props) {
        super(props);
        this.state = { Users: [] };

    }

    render() {
        const users = data.users;
        const props = this.props;
        const match = props.match;
        const history = props.history;
        const index = match.params.index;
        console.log(this.state.Users)
        console.log("props ", props, " match ", match, " history ", history, "index", index);

        return (


            <div>

                <div>
                    <h1>Details for {users[index].first} {users[index].last}</h1>
                </div>

                <table className="table">
                    <tr>
                        <th><p>Gender</p></th><td>{users[index].gender}</td>
                    </tr>
                    <tr><th><p>Firstname</p></th><td> {users[index].first}</td></tr>
                    <tr>
                        <th><p>Surname</p></th>
                        <td>{users[index].last}</td>
                        <td>
                            <img src={users[index].picture.large} alt='pic' />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <p>Street</p>
                        </th>
                        <td>{users[index].street}</td>
                    </tr>
                    <tr>
                        <th><p>City</p></th>
                        <td>{users[index].city}</td>
                    </tr>
                    <tr>
                        <th><p>ZIP</p></th>
                        <td>{users[index].zip}</td>

                    </tr>
                    <tr>
                        <th><p>Phone</p></th>
                        <td>{users[index].phone}</td>
                    </tr>
                    <tr>
                        <th><p>Cell</p></th>
                        <td>{users[index].cell}</td>
                    </tr>
                    <tr>
                        <th><p>Email</p></th>
                        <td>{users[index].email}</td>
                    </tr>
                </table>

                <ul>
                    <p><Link to="/All">Back</Link></p>
                    <p><Link to="/">Home</Link></p>
                </ul>
            </div>
        )
    }
}
export default details;
