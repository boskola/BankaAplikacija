import React from "react";
import { Row, Col, Button, Table, Form} from "react-bootstrap";
import BankaAxios from "./../../apis/BankaAxios";
import {withNavigation} from '../../routeconf'

class Racuni extends React.Component{
    constructor(props){
        super(props);

        const search = {
            jmbg: "",
            bankaId: ""
        }

        this.state = { 
                    racuni: [],
                    banke: [], 
                    search: search, 
                    pageNo: 0, 
                    hideSearch: true}
    }

    componentDidMount(){
        this.getRacuni(0); 
        this.getBanke(); 
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

    async getRacuni(newPageNo) {    
        const conf = {
          params : {
            pageNo: newPageNo,
            jmbg: this.state.search.jmbg,
            bankaId: this.state.search.bankaId
          }
        }
        try {
          let result = await BankaAxios.get("/racuni", conf);
          console.log(result.headers["total-pages"])
          console.log(result)
          this.setState({
             racuni: result.data,
             pageNo: newPageNo,
             totalPages: result.headers["total-pages"]
            });

        } catch (error) {
          console.log(error);
        }
    }

    goNext() {
          this.getRacuni(this.state.pageNo+1)
    }
    
    goPrevious() {
          this.getRacuni(this.state.pageNo-1)    
    }

    renderRacune() {
        return this.state.racuni.map((racun, index) => {
            return (
               <tr key={racun.id}>
                  <td>{racun.imePrezime}</td>
                  <td>{racun.jmbg}</td>
                  <td>{racun.stanjeRacuna}</td>
                  <td>{racun.brojRacuna}</td>
                  <td>{racun.nazivTipaRacuna}</td>
                  <td>{racun.nazivBanke}</td>
                  {window.localStorage['role'] == 'ROLE_ADMIN' ?
                        [<td key= {racun.id+"editt"}><Button variant="warning" style={{ width: "80px", height: "40px"}} onClick={() => this.goToEdit(racun.id)}>Edit</Button></td>,
                  <td key= {racun.id+"delete"}><Button variant="danger" style={{ width: "80px", height: "40px"}} onClick={() => this.delete(racun.id)}>Delete</Button></td>]
                  : null}
               </tr>
            );
         })
    }
    
    deleteFromState(racunId) {
        var racuni = this.state.racuni;
        racuni.forEach((element, index) => {
            if (element.id === racunId && element.stanjeRacuna==0) {
                racuni.splice(index, 1);
                this.setState({racuni: racuni});
            }
        });
    }

    delete(racunId) {
        BankaAxios.delete('/racuni/' + racunId)
        .then(res => {
            // handle success
            console.log(res);
            alert('Racun was deleted successfully!');
            window.location.reload();
            //this.deleteFromState(linijaId);
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    goToAdd() {
        this.props.navigate('/racuni/add');  
    }

    goToEdit(racunId) {
        this.props.navigate('/racuni/edit/'+ racunId); 
    }

    onInputChange(event) {
        const name = event.target.name;
        const value = event.target.value

        let search = this.state.search;
        search[name] = value;

        console.log(event.target.value);
        this.setState({ search })
        this.getRacuni(0); 
    }

    bankaSelectionChanged(e){
        const name = e.target.name;
        const value = e.target.value

        let search = this.state.search;
        search[name] = value;

        this.setState({ search })
        this.getRacuni(0);
    }

    renderSearchForm() {
        return (
            <>
            <Form style={{ width: "100%" }}>
                <Row><Col>
                    <Form.Group>
                        <Form.Label>Jmbg</Form.Label>
                        <Form.Control
                            name="jmbg"
                            as="input"
                            type="text"
                            onChange={(e) => this.onInputChange(e)}></Form.Control>
                    </Form.Group>
                </Col></Row>

                <Row>
                    <Col>
                    <Form.Label htmlFor="bankaId">Banka</Form.Label>
                    <Form.Select  name="bankaId" onChange={event => this.bankaSelectionChanged(event)}>
                        <option key=""></option>
                        {
                            this.state.banke.map((banka) => {
                                return (
                                    <option key={banka.id} value={banka.id}>{banka.naziv}</option>
                                )
                            })
                        }
                    </Form.Select>
                    </Col>
                </Row>
            </Form>
            <Row><Col>
                <Button className="mt-3" onClick={() => this.getRacuni(0)}>Search</Button>
            </Col></Row>
            </>
        );
    }

    render(){
        return(
            <div>
            <Col>
                <Row><h1>Racuni</h1></Row>
                <div>
                    <label>
                    <input type="checkbox" onChange={()=>this.setState({hideSearch: !this.state.hideSearch})}/>
                        Prikazi pretragu
                    </label>
                </div>
                <Row hidden={this.state.hideSearch} >
                    {this.renderSearchForm()}
                </Row>
                <br/>
                {window.localStorage['role']=='ROLE_ADMIN'?
                <Row>
                    <Button style={{ width: "80px", height: "40px"}}  onClick={() => this.goToAdd()}>Add</Button>
                </Row>: null}
                  <br/><br/>
                <Row>
                    <Table style={{marginTop:5}}>
                        <thead>
                            <tr>
                                <th>Ime i prezime</th>
                                <th>JMBG</th>
                                <th>Stanje</th>
                                <th>Broj racuna</th>
                                <th>Tip racuna</th>
                                <th>Banka</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.renderRacune()}
                        </tbody>                  
                    </Table>
                    <Button disabled={this.state.pageNo === 0} className="mr-2" style={{ width: "80px", height: "40px"}} onClick={()=>this.goPrevious()}>Prev</Button>
                    <Button disabled={this.state.pageNo===this.state.totalPages-1} style={{ width: "80px", height: "40px"}} onClick={()=>this.goNext()}>Next</Button>
                </Row>
            </Col>
            </div>
        );
    }
}

export default withNavigation(Racuni);