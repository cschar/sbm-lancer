import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import config from './config.js';

import '../node_modules/loaders.css/loaders.css'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

import Loader from 'react-loaders'


const SERVER_NAME = config.SERVER_NAME;

const PAGE_SIZE = 10;

class Pager extends Component {

  render(){
    let pages = Array.from(Array(this.props.totalPages).keys());

    return (
      <div>
        {pages.map((x) => {
          return ( <div key={"pageDiv"+x}>
            <button className="btn btn-outline-secondary"
             onClick={() =>
               this.props.callBack(SERVER_NAME+"/sumLogs?size="+PAGE_SIZE+"&page="+x)} > {x} </button>
            </div>
           )
        })}
        </div>
    )
  }
}

class App extends Component {

  constructor(){
    super();
    this.state = {
      quoteValue: "AAPL",
      userNameValue: "bob",
      data: [],
      pageData: null,
      status: "",
      loading: false
    }

    this.getSumLogs = this.getSumLogs.bind(this);

  
  }

  handleChangeQuote(event) {
    this.setState({quoteValue: event.target.value});
  }
  
  handleChangeUserName(event) {
    this.setState({userNameValue: event.target.value});
  }




  componentDidMount(){
    this.getSumLogs();
  }

  getSumLogs(pageUrl){
    this.setState({loading: true});
    
    let url = SERVER_NAME+"/sumLogs?size="+PAGE_SIZE;
    if(pageUrl !== undefined){
      url = pageUrl;
    }

   fetch(url, {
      // body: JSON.stringify(data), // must match 'Content-Type' header
      // cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
      // credentials: 'same-origin', // include, same-origin, *omit
      headers: {
        'content-type': 'application/json'
      },
      method: 'GET', // *GET, POST, PUT, DELETE, etc.
      // mode: 'cors', // no-cors, cors, *same-origin
      // redirect: 'follow', // manual, *follow, error
      // referrer: 'no-referrer', // *client, no-referrer
    }).then(function(response){
      response.json().then(function(data){
        console.log(data);
      
        let src_paths = [];

        this.setState({
          pageData: data.page,
          data: data._embedded.sumLogs,
          loading: false})
      }.bind(this))

    }.bind(this)).catch(function(err){
      console.log(err);
    })

  } 
  

  render() {

  

    return (
  <div className="App container">
        
    <div className="row">
    <div className="col-9">
        {this.state.data.map( function(x, idx){
          
          return (
            <div key={"imagediv"+x.lastName}>
                {x.firstName}
                <img key={idx + "img"}
                width="100%"
                src={SERVER_NAME+"/files/"+x.lastName.substr("renders".length)} />
              </div>
          )
        })}
    </div>
      <div className="col-3 bg-light">
        <div className="loaderHolderBig">
              
              <Loader type="ball-scale-multiple" active={this.state.loading}
              color="cornflowerblue"
                />
            
        </div>


        {/* <button className="btn btn-outline-primary" onClick={this.getSumLogs} >
          Get Images
        </button> */}

        {this.state.pageData !== null ?
          <Pager totalPages={this.state.pageData.totalPages}
                 callBack={this.getSumLogs}
                  />
      :
      (<div>no page </div>)}
            
          
      </div>
    </div>
  </div>
    );
  }
}

export default App;
