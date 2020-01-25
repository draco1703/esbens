import React, { Component } from "react";
import countryFactory from "./countryFactory";

class CountryTable extends Component {
  constructor(props) {
    super(props);

    this.jsonKeys = ["name", "capital", "region", "population", "area", "timezones", "borders", "topLevelDomain", "currencies", "languages"];

    this.state = {
      labels: [],
      countries: []
    };
  }

  componentDidMount() {
    new countryFactory((labels, countries) => {
      this.setState({ labels, countries });

      console.log(labels)
      console.log(countries)
    })
  }

  render() {
    let { labels, countries } = this.state
    return (

      <table className="table">
        <thead>
          <tr>{labels.map((label, index) => <th key={index}>{label}</th>)}</tr>
        </thead>

        <tbody>
          {countries.map((country, index) =>
            <tr key={index}>
              {this.jsonKeys.map((label, index) =>
                <td key={index}>
                  {this.multipleFinder(country[label])}
                </td>)}
            </tr>)}
        </tbody>
      </table>
    );
  }

  multipleFinder(testObj) {
    if (typeof testObj === 'object') {
      if (testObj.length === 1)
        return testObj[0];
      return `${testObj[0]} (+${testObj.length - 1})`;
    }
    return testObj;
  }
}
export default CountryTable;