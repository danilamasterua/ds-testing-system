import {createApp} from 'vue';
import DashboardComponent from "./dashboard-component.js";
export default {
    data(){
      return {
          command: "login",
          login: "",
          pass: ""
      }
    },
    methods:{
      proceedLogin(){
          $('#sign-in-btn').prop('disabled', true);
          $('#login-load-btn').show();
          $('#error-block').hide();
          let request={
              command: this.command,
              login: this.login,
              pass: this.pass
          }
          $.ajax({
              method: "POST",
              url: "do",
              data: JSON.stringify(request),
              dataType: 'json',
              success: function (response){
                  if(response.error===true){
                      $("#error-header").text(response.errorCode)
                      $("#error-body").text(response.stackTrace);
                      $("#error-block").show();
                  } else {
                      createApp(DashboardComponent).mount("#login-form");
                  }
              },
              error: function (response){
                  $("#error-header").text("ERROR");
                  $("#error-body").text(response);
                  $("#error-block").show();
              }
          })
      }
    },
    template:
            '<div class="d-flex justify-content-center my-1">' +
            '  <div class="centered-form-block" id="login-form">' +
            '    <div>' +
            '      <h1>Авторизація</h1>' +
            '      <form @submit.prevent="proceedLogin" class="mb-0">' +
            '        <label for="login" class="form-label my-0">Логін або Email</label>' +
            '        <input id="login" v-model="login" name="login" placeholder="name.surname" class="form-control mb-1" required>' +
            '        <label for="password" class="form-label my-0">Пароль</label>' +
            '        <input id="password" v-model="pass" name="password" placeholder="YourPassword" type="password" class="form-control mb-1" required>' +
            '        <button class="btn btn-primary" id="login-load-btn" type="button" style="display: none" disabled>\n' +
            '              <span class="spinner-border spinner-border-sm" aria-hidden="true"></span>\n' +
            '              <span class="visually-hidden" role="status">Loading...</span>\n' +
            '        </button>' +
            '        <button type="submit" id="sign-in-btn" class="btn btn-light mb-1"><i class="bi bi-arrow-right-circle"></i> Вхід</button>' +
            '      </form>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '</div>'
}