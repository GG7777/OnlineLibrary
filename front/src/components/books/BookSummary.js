import React, { Component }  from 'react';
import { Link } from 'react-router-dom'
import "./Book.css"

export default class BookSummary extends Component {

	render() {
	var book = this.props.book;	

    return (
			<div className="book">
					<img className="book__img" src={process.env.PUBLIC_URL + book.image_path} alt=''/>
					<div className="book__content content--book">
						<Link to={'/book/' + book.id}>
							
						</Link>
					</div>
			</div>
	)
	}
}
