import {createApp} from 'vue';
import LoginComponent from "../login-component.js";
import DashboardComponent from "../dashboard-component.js";

class User{
    login
    firstName
    lastName
    constructor(login, firstName, lastName) {
        this.login=login;
        this.firstName=firstName;
        this.lastName=lastName;
    }
}

$.ajax({
    method: 'GET',
    url: "do",
    data: {
        command: "getCurrentUser"
    },
    success: function (response){
        if(response.nosession===true){
            createApp(LoginComponent).mount('#login-form');
        } else {
            createApp(DashboardComponent).mount("#login-form");
        }
    }
})