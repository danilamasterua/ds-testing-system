import {createApp} from 'vue';
import LoginComponent from "../login-component.js";
import DashboardComponent from "../dashboard-component.js";


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