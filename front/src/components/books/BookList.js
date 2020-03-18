import React from 'react'
import BookSummary from './BookSummary';

const BookList = ({books}) => {
    return (
        <React.Fragment>
            { books && books.map((book,index) => {
                return (
                        <BookSummary book={book} key={index}/>
                )
            })}
        </React.Fragment>
    )
}

export default BookList;