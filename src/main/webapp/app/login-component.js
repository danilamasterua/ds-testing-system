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
                      console.log(response);
                  }
              }
          })
      }
    },
    template:
            '<div  class="d-flex justify-content-center my-1">\n' +
            '  <div class="centered-form-block" id="login-form">\n' +
            '    <div>\n' +
            '      <h1>Авторизація</h1>\n' +
            '      <form @submit="proceedLogin" class="mb-0">\n' +
            '        <label for="login" class="form-label my-0">Логін або Email</label>\n' +
            '        <input id="login" v-model="login" name="login" placeholder="name.surname" class="form-control mb-1" required>\n' +
            '        <label for="password" class="form-label my-0">Пароль</label>\n' +
            '        <input id="password" v-model="pass" name="password" placeholder="YourPassword" type="password" class="form-control mb-1" required>\n' +
            '        <button type="submit" class="btn btn-light mb-1"><i class="bi bi-arrow-right-circle"></i> Вхід</button>\n' +
            '      </form>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '</div>'
}