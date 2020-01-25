//Add imports here

class CountryFactory {

  constructor(props) {
    this.obs = []
    this.labels = [];
    this.countries = [];

    if (props !== undefined)
      this.addObs(props);

    Promise.all([this.getCountries(), this.getLabels()]).then(res => {
      this.countries = res[0]
      this.labels = res[1]
      this.notifyObs();
      this.interval = setInterval(() =>
        this.getCountries()
          .then(countries => {
            this.countries = countries;
            this.notifyObs();
          }, this),
        3000, this)
    }, this)
  }

  getCountries = async () => {
    let res = await fetch("http://localhost:3333/countries")
    let dat = await res.json()
    return dat
  }

  getLabels = async () => {
    let res = await fetch("http://localhost:3333/labels")
    let dat = await res.json()
    return dat
  }

  addObs = (callback) => {
    this.obs.push(callback);
  }

  notifyObs = () => {
    this.obs.forEach(callback => callback(this.labels, this.countries));
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }
}

export default CountryFactory;