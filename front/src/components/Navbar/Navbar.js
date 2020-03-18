import React, { Component } from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

class Navbar extends Component {
  render() {
    return (
      <header>
        <div className="content-wrap">
          <div className="nav-content">
            <Link className="logo" to="/">
              <span>Books</span>
              <img
                src="https://nervous-curie-57jpy.codesandbox.io/img/book-open-variant(1).png"
                height="20px"
                alt=""
              />
            </Link>
            <nav className="nav-list">
              <Link className="nav-item" to="/catalog">
                Каталог
              </Link>
              <Link className="nav-item" to="/popular">
                Популяное
              </Link>
              <Link className="nav-item" to="/signup">
                Зарегистрироваться
              </Link>
              <Link className="nav-item" to="/signin">
                Войти
              </Link>
            </nav>
          </div>
        </div>
      </header>
    );
  }
}

export default Navbar;
