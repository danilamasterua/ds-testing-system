import {createApp} from 'vue';
import UsercabinetComponent from "./dashboard-content-components/usercabinet/usercabinet-component.js";
import TestlistComponent from "./dashboard-content-components/testlist/testlist-component.js";

export default{
    data(){
        return{
            uac: false,
            app: undefined
        }
    },
    mounted(){
        console.log("Load dashboard");
        this.setUAC();
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
      },
      async setUAC(){
          let uac = false;
          await $.ajax({
              method: 'GET',
              url: "do",
              data: {
                  command: "getCurrentUser"
              },
              success: function (response) {
                  console.log(response);
                  console.log(response.userAccessLevel);
                  if(response.userAccessLevel==='TEACHER'||response.userAccessLevel==='ADMIN'){
                      uac=true;
                  }
              },
              error: function (resp) {
                  $("#error-header").text('ERROR');
                  $("#error-body").text('Unexpected error');
                  $("#error-block").show();
              }
          })
          this.uac = uac;
      }
    },
    template:
        '<div class="btn-group mb-2">' +
        '   <button class="btn btn-primary" @click="loadUserCabinet()">Профіль</button>' +
        '   <button class="btn btn-light" @click="loadTestList()">Тести</button>' +
        '   <button v-if="uac" class="btn btn-light">Користувачі</button> ' +
        '</div>' +
        '<div class="dashboard-content" id="dashboard-content"></div>'
}