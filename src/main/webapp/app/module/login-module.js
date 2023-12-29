import {createApp} from 'vue';
import LoginComponent from "../login-component.js";
createApp(LoginComponent).mount('#login-form');
let data={
    command: "login",
    login: "",
    pass: ""
}
function onsubmit(){
    console.log("login:" + data.login + " pass:");
}