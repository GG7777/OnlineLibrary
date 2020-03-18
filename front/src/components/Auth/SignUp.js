import React, { Component } from "react";
import Redirect from "react-dom";
import "./Auth.css";

export default class SignUp extends Component {
  state = {
    username: "",
    email: "",
    password: ""
  };

  changeHandler = e => {
    var inputId = e.target.id;
    var newState = this.state;
    newState[inputId] = e.target.value;
    this.setState(newState);
  };

  clickHandler = () => {
    fetch("http://192.168.0.43:14882/api/registration", {
      crossDomain: true,
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(this.state)
    })
      .then(response => {
        console.log(response);
        return response.json();
      })
      .then(responseJson => {
        return <Redirect to="/signin" />;
        //console.log(responseJson);
      });
  };

  render() {
    return (
      <div className="auth-wrapper">
        <input
          className="auth-field"
          id="username"
          placeholder="Username"
          onChange={this.changeHandler.bind(this)}
        />
        <input
          className="auth-field"
          id="email"
          placeholder="Email"
          onChange={this.changeHandler.bind(this)}
        />
        <input
          className="auth-field"
          id="password"
          placeholder="Password"
          onChange={this.changeHandler.bind(this)}
        />
        <button className="auth-button" onClick={this.clickHandler.bind(this)}>
          Submit
        </button>
      </div>
    );
  }
}
