import {createApp} from 'vue';
import UsercabinetComponent from "./dashboard-content-components/usercabinet/usercabinet-component.js";

export default{
    mounted(){
        console.log("Load dashboard");
        this.loadUserCabinet();
    },
    methods: {
      loadUserCabinet(){
          createApp(UsercabinetComponent).mount("#dashboard-content");
      }
    },
    template:
        '<div class="btn-group">' +
        '   <button class="btn btn-light">Профіль</button>' +
        '   <button class="btn btn-light" disabled>Тести</button> ' +
        '</div>' +
        '<div class="dashboard-content" id="dashboard-content"></div>'
}