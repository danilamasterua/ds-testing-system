import {createApp} from 'vue';
import changepasswordComponent from "./changepassword-component.js";
export default {
    mounted() {
        $.ajax({
            method: 'GET',
            url: "do",
            data: {
                command: "getCurrentUser"
            },
            success: function (response) {
                let fullName = response.firstName + " " + response.lastName;
                $("#usercard-fullName").text(fullName);
                $("#usercard-email").text(response.email);
            },
            error: function (resp) {
                $("#error-header").text('ERROR');
                $("#error-body").text('Unexpected error');
                $("#error-block").show();
            }
        })
    },
    methods:{
      openChangePasswordForm(){
          createApp(changepasswordComponent).mount('#usercabinet-do');
      }
    },
    template:
        '<h2>Ваш профіль</h2>' +
        '<div class="dashboard-content-child">' +
        '   <div class="user-card">' +
        '       <div style="display: inline-flex; width: 100%;">' +
            '       <h4 id="usercard-fullName"></h4>' +
            '       <div style="display: flex; justify-content: space-between; margin-left: auto; align-items: center">' +
            '           <div class="btn-group">' +
            '               <button class="btn btn-light" @click="openChangePasswordForm()">Змінити пароль</button>' +
            '               <a href="do?command=exit" class="btn btn-danger">Вийти</a>' +
            '           </div>' +
            '       </div>' +
        '       </div>' +
        '       <p><b>Email:</b><a style="word-wrap:anywhere" id="usercard-email"></a></p>' +
        '   </div>' +
        '   <div class="dashboard-action-block" id="usercabinet-do">' +
        '   </div>' +
        '</div>'
}