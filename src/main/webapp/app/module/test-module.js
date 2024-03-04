import {createApp} from 'vue';
import CreationTestComponent from "../component/test-management-components/creation-test-component.js";
import TestDashboardComponent from "../component/test-management-components/test-dashboard-component.js";

$.ajax({
    url: 'do',
    data:{
        command: 'getTestPageStatus'
    },
    success: function (response) {
        if(response.testPageStatus){
            createApp(CreationTestComponent).mount('#test-management-block');
        }
        else {
            createApp(TestDashboardComponent).mount('#test-management-block');
        }
    }
})