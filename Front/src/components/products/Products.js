import React from "react";
import { Row, Col, Button, Table, Form } from 'react-bootstrap'
import Axios from "../../apis/Axios";
import { withParams, withNavigation } from '../../routeconf';
import './../../index.css';

class Products extends React.Component {

    constructor(props) {
        super(props)

        this.pageNo = 0;
        this.totalPages = 0;

        const search = {
            priceFrom: "",
            priceTo: "",
            categoryId: ""
        }

        this.state = {
            products: [],
            categories: [],
            search: search,
            showing: false,
            quantity: 0
        }

    }

    componentDidMount() {
        this.getProducts(0)
        this.getCategories()
    }

    async getProducts(newPageNo) {

        let config = {
            params: {
                priceFrom: this.state.search.priceFrom,
                priceTo: this.state.search.priceTo,
                categoryId: this.state.search.categoryId,
                pageNo: newPageNo
            }
        }

        try {
            let result = await Axios.get("/products", config)
            this.pageNo = newPageNo
            this.totalPages = result.headers["Total-Pages"]
            this.setState({
                products: result.data
            })
        } catch (error) {
            console.log(error)

        }
    }

    getCategories() {
        Axios.get("/categories")
            .then((res) => {
                this.setState({ categories: res.data })
            })
            .catch((error) => {
                console.log(error)
            })
    }

    goToAdd() {
        this.props.navigate("/products/add")
    }

    goToEdit(id) {
        this.props.navigate("/products/edit/" + id)
    }

    async buy(productId) {

        try {
            var body = {
                "productId": productId,
                "quantity": this.state.quantity
            }
            let response = await Axios.post("/products/buy", body);
            alert("Uspesno kupljeno")
            this.getProducts(0)

        } catch (error) {
            alert("Ne moze se sacuvati kupovina")
        }
    }

    deleteFromState(productId) {
        var products = this.state.products
        products.forEach((element, index) => {
            if (element.id === productId) {
                products.splice(index, 1)
                this.setState({ products: products })
            }
        })
    }

    goToDelete(productId) {
        Axios.delete("/products/" + productId)
            .then(res => {
                console.log(res)
                alert("Uspesno brisanje proizvoda")
                this.deleteFromState(productId)
            })
            .catch(error => {
                console.log(error)
                this.getProducts(0)
                // window.location.reload()
            })
    }

    onInputChange(event) {
        const name = event.target.name;
        const value = event.target.value

        let search = this.state.search;
        search[name] = value;

        this.setState({ search })
    }

    onQuantityChange(e) {
        this.state.quantity = e.target.value;
    }

    renderSearchForm() {
        return (
            <>
                <Form style={{ width: "100%" }}>
                    <Row><Col>
                        <Form.Group>
                            <Form.Label>Cena proizvoda od</Form.Label>
                            <Form.Control
                                name="priceFrom"
                                as="input"
                                type="number"
                                onChange={(e) => this.onInputChange(e)}>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                    </Row>
                    <Row><Col>
                        <Form.Group>
                            <Form.Label>Cena proizvoda do</Form.Label>
                            <Form.Control
                                name="priceTo"
                                as="input"
                                type="number"
                                onChange={(e) => this.onInputChange(e)}>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Group>
                                <Form.Label>Kategorija</Form.Label>
                                <Form.Select
                                    name="categoryId"
                                    onChange={(e) => this.onInputChange(e)}>
                                    <option value=""></option>
                                    {this.state.categories.map((Category) => {
                                        return (
                                            <option value={Category.id}>{Category.name}</option>
                                        );
                                    })}
                                </Form.Select>
                            </Form.Group>
                        </Col>
                    </Row>
                </Form>
                <Row><Col>
                    <Button className="mt-3" onClick={() => this.getProducts()}>Search</Button>
                    <br />
                    <br />
                </Col>
                </Row>
            </>
        )
    }

    render() {
        return (
            <Col>
                <Row>Proizvodi</Row>
                <label>
                    <input type="checkbox" onChange={() => this.setState({ showing: !this.state.showing })} />
                    Search
                </label>
                <div>
                    <Row hidden={this.state.showing}>
                        {this.renderSearchForm()}
                    </Row>
                </div>
                <Row><Col>
                    <Button onClick={() => this.goToAdd()}>Add</Button>
                </Col></Row>

                <Row><Col>

                </Col></Row>
                <Row><Col>

                    <Table style={{ marginTop: 5 }}>
                        <thead>
                            <tr>
                                <th>Naziv</th>
                                <th>Cena</th>
                                <th>Stanje</th>
                                <th>Kategorija</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.products.map((product) => {
                                return (
                                    <tr key={product.id}>
                                        <td>{product.name}</td>
                                        <td>{product.price}</td>
                                        <td>{product.quantity}</td>
                                        <td>{product.category.name}</td>
                                        <td><Button variant="warning" onClick={() => this.goToEdit(product.id)}>Edit</Button></td>
                                        <td><Button variant="danger" onClick={() => this.goToDelete(product.id)}>Delete</Button></td>

                                        {window.localStorage['role'] == 'KORISNIK' ?
                                        <Form.Group>
                                            <Form.Label htmlFor="quantity"></Form.Label>
                                            <Form.Control id="quantity" type="number" onChange={(e) => this.onQuantityChange(e)} /> <br />
                                        </Form.Group> : null}


                                        {window.localStorage['role'] == 'KORISNIK' ?
                                            <td><Button variant="success" onClick={() => this.buy(product.id)}>Kupi</Button></td> : null}
                                    </tr>
                                );
                            })}
                        </tbody>
                    </Table>
                    <Button disabled={this.pageNo === 0} onClick={() => this.getProducts(this.pageNo - 1)} className="mr-3">Prev</Button>
                    <Button disabled={this.pageNo == this.totalPages - 1} onClick={() => this.getProducts(this.pageNo + 1)} className="mr-3">Next</Button>
                </Col></Row>
            </Col>
        )
    }

}

export default withNavigation(withParams(Products))