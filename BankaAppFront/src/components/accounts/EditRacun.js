import React from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import BankaAxios from "./../../apis/BankaAxios";
import {withParams, withNavigation} from '../../routeconf';

class EditRacun extends React.Component{

    constructor(props) {
        super(props);

        this.state = {id: -1,
            imePrezime: "",
            jmbg: "",
            brojRacuna: "",
            nazivTipaRacuna: "",
            idTipaRacuna: 0,
            nazivBanke: "",
            stanjeRacuna: 0.00,
            idBanke: -1};
    }

    componentDidMount(){
        this.getRacunById(this.props.params.id);
    }

    getRacunById(racunId) {
        BankaAxios.get('/racuni/' + racunId)
        .then(res => {
            // handle success
            console.log(res);
            this.setState({ 
                id: res.data.id,
                imePrezime: res.data.imePrezime,
                jmbg: res.data.jmbg,
                brojRacuna: res.data.brojRacuna,
                nazivTipaRacuna: res.data.nazivTipaRacuna,
                idTipaRacuna: res.data.idTipaRacuna,
                nazivBanke: res.data.nazivBanke,
                stanjeRacuna: res.data.stanjeRacuna,
                idBanke: res.data.idBanke});
                            
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }

    onInputChange(e) {
        let name = e.target.name;
        let value = e.target.value;
        console.log(value);
    
        this.setState((state, props) => ({
            [name]: value,
          }));
    }

    edit() {
        var params = {
            'id': this.state.id,
            'imePrezime': this.state.imePrezime,
            'jmbg': this.state.jmbg,
            'brojRacuna': this.state.brojRacuna,
            'nazivTipaRacuna': this.state.nazivTipaRacuna,
            'idTipaRacuna': this.state.idTipaRacuna,
            'nazivBanke': this.state.nazivBanke,
            'stanjeRacuna': this.state.stanjeRacuna,
            'idBanke': this.state.idBanke
        };

        console.log("EDIT PARAMETRI " + params);

        BankaAxios.put('/racuni/' + this.state.id, params)
        .then(res => {
            // handle success
            console.log(res);
            alert('Racun was edited successfully!');
            this.props.navigate('/racuni');
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }


    render(){
        return(
            <>
            <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                <Row><h1>Izmena racuna</h1></Row>
                <Form>
                    <Form.Group>
                    <Form.Label htmlFor="imePrezime">Ime i prezime</Form.Label>
                    <Form.Control 
                        id="imePrezime"
                        name="imePrezime"
                        value={this.state.imePrezime}
                        type="text"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>

                    <Form.Group>
                    <Form.Label htmlFor="jmbg">JMBG</Form.Label>
                    <Form.Control 
                        id="jmbg"
                        name="jmbg"
                        value={this.state.jmbg}
                        type="number"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>

                    <Form.Group>
                    <Form.Label htmlFor="brojRacuna">Broj racuna</Form.Label>
                    <Form.Control 
                        id="brojRacuna"
                        value={this.state.brojRacuna}
                        name="brojRacuna"
                        type="text"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>

                    <Button style={{ marginTop: "25px" }} onClick={(event)=>{this.edit(event);}}>
                        Edit
                    </Button>
                </Form>
                </Col>
                <Col></Col>
            </Row>
            
            </>
        )
    }

}

export default withNavigation(withParams(EditRacun));