import React from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import BankaAxios from "../../apis/BankaAxios";
import { withNavigation } from "../../routeconf";

class AddRacun extends React.Component{
    constructor(props) {
        super(props);

        let racun = {
            imePrezime: "",
            jmbg: "",
            brojRacuna: "",
            idBanke: 0,
            idTipaRacuna: 0 
        }
        this.state = {racun: racun, banke: [], tipovi: []}; 
    }

    componentDidMount(){
        this.getBanke();
        this.getTipovi();
      }

      async getBanke() {
        try {
          let result = await BankaAxios.get("/banke");
          this.setState({ banke: result.data });
        } catch (error) {
          alert("Could not fetch genres.");
          console.log(error);
        }
      }

      async getTipovi() {
        try {
          let result = await BankaAxios.get("/tip");
          this.setState({ tipovi: result.data});
        
        } catch (error) {
          alert("Could not fetch genres.");
          console.log(error);
        }
      }

    create = () => {
        var params = {
            
            imePrezime: this.state.racun.imePrezime,
            jmbg: this.state.racun.jmbg,
            brojRacuna: this.state.racun.brojRacuna,
            idBanke: this.state.racun.idBanke,
            idTipaRacuna: this.state.racun.idTipaRacuna
            
        };

        BankaAxios.post('/racuni', params)
        .then(res => {
            // handle success
            console.log(res);
           
            alert('Racun was added successfully!');
            this.props.navigate('/racuni'); 
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again 9999!');
         });
    }

    onInputChange(e) {
        let name = e.target.name;
        let value = e.target.value;

        let racun = this.state.racun;
        racun[name] = value;
    
        this.setState({racun: racun });
    }

    bankaSelectionChanged(e){
        let idBanke = e.target.value;

        let racun = this.state.racun;
        racun.idBanke = idBanke;

        this.setState({racun: racun});
    }

    tipSelectionChanged(e){
        let idTipaRacuna = e.target.value;

        let racun = this.state.racun;
        racun.idTipaRacuna = idTipaRacuna;

        this.setState({racun: racun});
    }

    render(){
        return(
            <>
            <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                <h1>Dodavanje novog racuna</h1>
                <Form>
                    <Form.Group>
                    <Form.Label htmlFor="imePrezime">Ime i prezime</Form.Label>
                    <Form.Control 
                        id="imePrezime"
                        name="imePrezime"
                        type="text"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>

                    <Form.Group>
                    <Form.Label htmlFor="jmbg">JMBG</Form.Label>
                    <Form.Control 
                        id="jmbg"
                        name="jmbg"
                        type="number"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>

                    <Form.Group>
                    <Form.Label htmlFor="banke">Banka</Form.Label>
                    <Form.Select  name="idBanke" onChange={event => this.bankaSelectionChanged(event)}>
                        <option key=""></option>
                        {
                            this.state.banke.map((banka) => {
                                return (
                                    <option key={banka.id} value={banka.id}>{banka.naziv}</option>
                                )
                            })
                        }
                    </Form.Select>
                    </Form.Group>

                    <Form.Group>
                    <Form.Label htmlFor="brojRacuna">Broj racuna</Form.Label>
                    <Form.Control 
                        id="brojRacuna"
                        name="brojRacuna"
                        type="text"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>

                    <Form.Group>
                    <Form.Label htmlFor="tipRacuna">Tip racuna</Form.Label>
                    <Form.Select  name="idTipaRacuna" onChange={event => this.tipSelectionChanged(event)}>
                        <option key=""></option>
                        {
                            this.state.tipovi.map((tip) => {
                                return (
                                    <option key={tip.id} value={tip.id}>{tip.naziv}</option>
                                )
                            })
                        }
                    </Form.Select>
                    </Form.Group>

                    <Button style={{ marginTop: "25px" }} onClick={(event)=>{this.create(event);}}>
                        Add
                    </Button>
                </Form>
                </Col>
                <Col></Col>
            </Row>
            </>
        )
    }
}

export default withNavigation(AddRacun);