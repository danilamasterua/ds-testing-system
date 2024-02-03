import {createApp} from 'vue';
import availabletestsComponent from "./availabletests-component.js";
import ownedtestsComponent from "./ownedtests-component.js";
export default {
    data() {
        return {
            uac: false,
            app: undefined,
        }
    },
    mounted(){
        this.setUAC();
        this.getTests();
    },
    methods:{
        async setUAC(){
            let uac = false;
            await $.ajax({
                method: 'GET',
                url: "do",
                data: {
                    command: "getCurrentUser"
                },
                success: function (response) {
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
        },
        getTests(){
              if(this.app!==undefined){
                  this.app.unmount();
              }
              this.app = createApp(availabletestsComponent);
              this.app.mount('#testlist-do');
        },
        getMyTests(){
            this.app.unmount();
            this.app = createApp(ownedtestsComponent);
            this.app.mount('#testlist-do');
        }
    },
    template:
        '<h2>Тести</h2>' +
        '<div class="d-flex justify-content-center">' +
        '   <div v-if="uac" class="btn-group my-1">' +
        '       <button class="btn btn-light" @click="getTests()">Доступні тести</button>' +
        '       <button class="btn btn-light" @click="getMyTests()">Мої тести</button>' +
        '   </div>' +
        '</div>' +
        '<div id="testlist-do" class="dashboard-content-child">' +
        '</div>'
}