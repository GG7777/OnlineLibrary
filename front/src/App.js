import React from "react";
import Navbar from "./components/Navbar/Navbar";
import Search from "./components/Search/Search";
import SignUp from "./components/Auth/SignUp";
import SignIn from "./components/Auth/SignIn";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import "./styles.css";

export default function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Search />
        <Switch>
          <Route exact path="/">
            Home
          </Route>
          <Route path="/catalog">catalog</Route>
          <Route path="/popular">popular</Route>
          <Route path="/signup">
            <SignUp />
          </Route>
          <Route path="/signin">
            <SignIn />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}
