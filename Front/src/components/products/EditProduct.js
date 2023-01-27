import React from "react";
import Axios from "../../apis/Axios";
import { Button, Col, Form, Row } from 'react-bootstrap';
import { withNavigation, withParams } from '../../routeconf'

class EditProduct extends React.Component {

    state = {
        productId: -1,
        productName: "",
        productPrice: 0,
        productQuantity: 0,
        categoryId: -1,
        categoryName: "",
        categories: []
    }

    componentDidMount() {
        var id = this.props.params.id
        this.getCategories()
        this.getProductById(id)
    }

    async getCategories() {
        try {
            let result = await Axios.get("/categories")
            let categories = result.data
            this.setState({ categories: categories })
            console.log("test1")
        } catch (error) {
            console.log(error)
            alert("Ne mogu se naci kategorije")
        }
    }

    getProductById(productId) {
        Axios.get("/products/" + productId)
            .then(res => {
                console.log(res)
                this.setState({
                    productId: res.data.id,
                    productName: res.data.name,
                    productPrice: res.data.price,
                    productQuantity: res.data.quantity,
                    categoryId: res.data.category.id,
                    categoryName: res.data.category.name  
                })
            })
            .catch(error => {
                console.log(error)
            })
    }

    edit(productId) {
        var params = {
            "id": this.state.productId,
            "name": this.state.productName,
            "price": this.state.productPrice,
            "quantity": this.state.productQuantity,
            "categoryId": this.state.categoryId,
            "categoryName": this.state.categoryName
        }

        Axios.put("/products/" + productId, params)
            .then(res => {
                console.log(res)
                this.props.navigate("/products")
            })
            .catch(error => {
                console.log(error)
            })
    }

    onNameChange(e) {
        console.log(e.target.value)
        this.setState({ productName: e.target.value })
    }

    onPriceChange(e) {
        console.log(e.target.value)
        this.setState({ productPrice: e.target.value })
    }

    onQuantityChange(e) {
        console.log(e.target.value)
        this.setState({ productQuantity: e.target.value })
    }

    categorySelectionChanged(event) {
        let categoryId = event.target.value
        let category = this.state.categories.find((category) => category.id == categoryId)

        this.state.productCategoryId = category.id
        this.state.productCategoryName = category.name
    }

    render() {
        return (
            <>
                <Row>
                    <Col></Col>
                    <Col xs="12" sm="10" md="8">
                        <h1>Izmena proizvoda</h1>
                        <Form>
                            <Form.Group>
                                <Form.Label htmlFor="name">Naziv</Form.Label>
                                <Form.Control id="name" name="name" value={this.state.productName} onChange={(e) => this.onNameChange(e)} /> <br />
                            </Form.Group>

                            <Form.Group>
                                <Form.Label htmlFor="price">Cena</Form.Label>
                                <Form.Control id="price" name="price" type="number" value={this.state.productPrice} onChange={(e) => this.onPriceChange(e)} /> <br />
                            </Form.Group>

                            <Form.Group>
                                <Form.Label htmlFor="quantity">Stanje</Form.Label>
                                <Form.Control id="quantity" name="quantity" type="number" value={this.state.productQuantity} onChange={(e) => this.onQuantityChange(e)} /> <br />
                            </Form.Group>

                            <Form.Group>
                                <Form.Label htmlFor="categoryId">Kategorija</Form.Label>
                                <Form.Control as="select" id="categoryId" onChange={event => this.categorySelectionChanged(event)}>
                                    <option>{this.state.productCategoryName}</option>
                                    {
                                        this.state.categories.map((category) => {
                                            return (
                                                <option key={category.id} value={category.id}>{category.name}</option>
                                            )
                                        })
                                    }
                                </Form.Control><br />
                            </Form.Group>
                            <Button onClick={() => this.edit(this.state.productId)}>Edit</Button>
                        </Form>
                    </Col>
                    <Col></Col>
                </Row>
            </>
        )
    }



}

export default withParams(withNavigation(EditProduct))