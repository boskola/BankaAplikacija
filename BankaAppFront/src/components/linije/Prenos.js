import React from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import BankaAxios from "./../../apis/BankaAxios";
import {withParams, withNavigation} from '../../routeconf';


class Prenos extends React.Component{

    constructor(props) {
        super(props);

        this.state = {
            brojRacunaPlatioca: "",
            brojRacunaPrimaoca: "",
            iznos: -1
            };
    }

    prenos(){
        BankaAxios.put('/racuni/prenos/' + this.state.brojRacunaPlatioca + "/" + this.state.brojRacunaPrimaoca + "/" + this.state.iznos)
        .then(res => {
            // handle success
            console.log(res);
            alert('Prenos was successfull!');
            this.props.navigate('/racuni');
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again 123!');
         });
    }

    onInputChange(e) {
        let name = e.target.name;
        let value = e.target.value;
        console.log(value);
    
        this.setState((state, props) => ({
            [name]: value
          }));
    }

    render(){
        return(
            <>
            <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                <Row><h1>Prenos sredstava</h1></Row>
                <Form>
                    <Form.Group>
                    <Form.Label htmlFor="brojRacunaPlatioca">Broj racuna uplatioca</Form.Label>
                    <Form.Control 
                        id="brojRacunaPlatioca"
                        name="brojRacunaPlatioca"
                        type="text"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>
                    <Form.Group>
                    <Form.Label htmlFor="brojRacunaPrimaoca">Broj racuna primaoca</Form.Label>
                    <Form.Control 
                        id="brojRacunaPrimaoca"
                        name="brojRacunaPrimaoca"
                        type="text"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>
                    <Form.Group>
                    <Form.Label htmlFor="iznos">Iznos</Form.Label>
                    <Form.Control 
                        id="iznos"
                        name="iznos"
                        type="number"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>
                    <Button style={{ marginTop: "25px" }} onClick={(event)=>{this.prenos(event);}}>
                        Izvrsi prenos
                    </Button>
                </Form>
                </Col>
                <Col></Col>
            </Row>
            </>
        )
    }

}

export default withNavigation(withParams(Prenos));