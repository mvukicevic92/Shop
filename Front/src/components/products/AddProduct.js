import React from 'react';
import { Row, Col, Form, Button } from "react-bootstrap";
import Axios from '../../apis/Axios';
import {withParams ,withNavigation} from '../../routeconf';

class AddProduct extends React.Component{

    constructor(props){
        super(props)

        let product = {
            name: "",
            price: 0,
            quantity: 0,
            category: null
        }

        this.state = { product: product, categories: []}
    }

    componentDidMount(){
        this.getCategories()
    }

    async getCategories(){
        try {
            let result = await Axios.get("/categories")
            let categories = result.data
            this.setState({categories: categories})
            console.log("ucitavanje kategorija")
        } catch (error) {
            console.log(error)
        }
    }

    async create(e){
        e.preventDefault()

        try {
            let product = this.state.product
            let productDTO = {
                name: product.name,
                price: product.price,
                quantity: product.quantity,
                category: product.category,
            }

            let response = await Axios.post("/products", productDTO)
            this.props.navigate("/products")
        } catch (error) {
            alert("Ne moze se sacuvati proizvod")
        }
    }

    valueInputChanged(e) {
        let input = e.target;

        let name = input.name;
        let value = input.value;

        let product = this.state.product;
        product[name] = value;

        this.setState({ product: product });
    }

    categorySelectionChanged(e){
        let categoryId = e.target.value
        let category = this.state.categories.find((category) => category.id == categoryId)

        let product = this.state.product
        product.category = category
        

        this.setState({ product: product})
    }

    render() {
        return (
            <>
                <Row>
                    <Col></Col>
                    <Col xs="12" sm="10" md="8">
                        <h1>Dodaj proizvod</h1>

                        <Form>
                            <Form.Group>
                                <Form.Label>Naziv proizvoda</Form.Label>
                                <Form.Control
                                    id="name" name="name"  onChange={(e) => this.valueInputChanged(e)} /> <br />
                            </Form.Group>

                            <Form.Group>
                                <Form.Label>Cena proizvoda</Form.Label>
                                <Form.Control type="number" id="price" name="price" onChange={(e) => this.valueInputChanged(e)} /> <br />
                            </Form.Group>

                            <Form.Group>
                                <Form.Label>Stanje</Form.Label>
                                <Form.Control id="quantity" name="quantity" type='number' onChange={(e) => this.valueInputChanged(e)} /> <br />
                            </Form.Group>

                            <Form.Group>
                                <Form.Label>Kategorija</Form.Label>
                                <Form.Select name="category" onChange={event => this.categorySelectionChanged(event)}>
                                    <option></option>
                                    {
                                        this.state.categories.map((category) => {
                                            return (
                                                <option key={category.id} value={category.id}>{category.name}</option>
                                            )
                                        })
                                    }
                                </Form.Select><br />
                            </Form.Group>

                            <Button onClick={(event) => { this.create(event); }}>Add</Button>
                        </Form>
                    </Col>
                    <Col></Col>
                </Row>
            </>
        )
    }

}

export default withNavigation(AddProduct);