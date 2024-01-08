export default {
    data(){
      return{
          oldpwd:'',
          npwd1:'',
          npwd2:''
      }
    },
    methods: {
        checkNewPasswordValidFormat() {
            const template = /^.*(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@?]).*$/;
            return template.test(this.npwd1);
        },
        checkPasswordsSame(){
            return this.npwd1===this.npwd2;
        },
        prepareFormSubmit(){
            let validFormat = this.checkNewPasswordValidFormat();
            let same = this.checkPasswordsSame();

            $('#pwd-chng-error-block').show();
            if(validFormat && same){
                $('#pwd-chng-error-message').text("Формати правильні")
            } else {
                let message = "";
                if (!validFormat) {
                    message += "</br>Неправильний формат паролю"
                }
                if (!same) {
                    message += "</br>Паролі не співпадають"
                }
                $('#pwd-chng-error-message').html(message);
            }
        }
    },
    template:
        '<div>' +
        '   <div id="pwd-chng-error-block" class="alert alert-warning" style="display: none">' +
        '       <p id="pwd-chng-error-message"></p>' +
        '   </div>' +
        '   <form @submit.prevent="prepareFormSubmit()">' +
        '       <label for="oldpwd-input" class="form-label my-0">Старий пароль</label>' +
        '       <input id="oldpwd-input" type="password" v-model="oldpwd" name="old-pwd" class="form-control mb-1">' +
        '       <label for="npwd1-input" class="form-label my-0">Новий пароль</label>' +
        '       <input id="npwd1-input" type="password" v-model="npwd1" name="npwd1" class="form-control mb-1">' +
        '       <label for="npwd2-input" class="form-label">Повторіть пароль</label>' +
        '       <input id="npwd2-input" type="password" v-model="npwd2" name="npwd2" class="form-control mb-1">' +
        '       <button type="submit" class="btn btn-primary">Змінити пароль</button>' +
        '   </form>' +
        '</div>'
}