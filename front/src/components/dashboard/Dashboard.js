/*eslint-disable*/
import React, { Component } from 'react'
import BookList from '../books/BookList'

class Dashboard extends Component {
    state = {
        books: []
    }

    componentDidMount()
    {
        fetch('http://192.168.0.38:14882/api/books?start=0&stop=1', {
            crossDomain:true,
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': 'http://192.168.0.38:14882/',
            },
        }).then(response => response.json())
        .then(responseJson => {
            var new_state = this.state;
            new_state.books = responseJson;
            this.setState(new_state);
        });
    }

    render() {
        let books = this.state.books.map(value =>  <div>{value.toString()}</div>)
    

        return (
            <div className="page-layout">
                <div className="row center-xs">
                    <div className="dashboard--grid col-xs-12">
                        {books ? <BookList books={books} /> : ""}
                    </div>
                </div>
            </div>
        )
    }
}

export default Dashboard;