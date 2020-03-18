import React, { Component } from "react";
import "./Auth.css";

export default class SignIn extends Component {
  state = {
    username: "",
    password: ""
  };

  changeHandler = e => {
    var inputId = e.target.id;
    var newState = this.state;
    newState[inputId] = e.target.value;
    this.setState(newState);
  };

  clickHandler = () => {
    fetch("http://192.168.0.43:14882/login", {
      credentials: "include",
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      body: `username=${this.state.username}&password=${this.state.password}`
    })
      .then(response => {
        console.log(response);
        return response.json();
      })
      .then(responseJson => {
        console.log(responseJson);
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
