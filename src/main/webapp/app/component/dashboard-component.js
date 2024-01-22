import {createApp} from 'vue';
import UsercabinetComponent from "./dashboard-content-components/usercabinet/usercabinet-component.js";
import TestlistComponent from "./dashboard-content-components/testlist/testlist-component.js";

export default{
    data(){
        return{
            app: undefined
        }
    },
    mounted(){
        console.log("Load dashboard");
        this.loadUserCabinet();
    },
    methods: {
      loadUserCabinet(){
          if(this.app!==undefined) {
              this.app.unmount();
          }
          console.log("Load user cabinet");
          this.app = createApp(UsercabinetComponent);
          this.app.mount("#dashboard-content");
      },
      loadTestList(){
          this.app.unmount();
          console.log("Load test list");
          this.app = createApp(TestlistComponent);
          this.app.mount("#dashboard-content");
      }
    },
    template:
        '<div class="btn-group">' +
        '   <button class="btn btn-primary" @click="loadUserCabinet()">Профіль</button>' +
        '   <button class="btn btn-light" @click="loadTestList()">Тести</button> ' +
        '</div>' +
        '<div class="dashboard-content" id="dashboard-content"></div>'
}