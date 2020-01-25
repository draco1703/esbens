import React from 'react';
import { StyleSheet, Text, ScrollView, Alert } from 'react-native';

const URL = "https://estudie.gottsche.dk/CA3/api/starwars/people/random";

export default class SWFetch extends React.Component {
    constructor(props) {
        super(props)
        this.state = { person: "" };
    }

    async componentDidMount() {
        try {
            const apitest = await fetch(URL).then(res => res.json());
            this.setState({ person: apitest });
        } catch (err) {
        }
    }

    render() {
        const person = this.state.person;
        return (
            <ScrollView>
                <Text>{person.name}</Text>
                <Text>{
                    'Hair colour: ' + person.hair_color + '\n' + 'Skin colour: ' + person.skin_color + '\n' + 'Eye colour: ' + person.eye_color + '\n' + 'Birth: ' + person.birth_year + '\n' + 'Height: ' + person.height + '\n' + 'Mass: ' + person.mass + '\n' + 'Gender: ' + person.gender + '\n'
                }</Text>
            </ScrollView>
        );
    }
}