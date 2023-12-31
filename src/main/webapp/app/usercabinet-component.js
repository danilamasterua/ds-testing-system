export default {
    data(){
      return{
          firstName:"",
          lastName:"",
          email:""
      }
    },
    mounted() {
        $.ajax({
            method: 'GET',
            url: "do",
            data: {
                command: "getCurrentUser"
            },
            success: function (response){
                this.firstName = response.firstName;
                console.log(response.firstName);
                this.lastName = response.lastName;
                this.email = response.email;
            }
        })
    },
    template:
        '<h1>Ваш профіль</h1>' +
        '<h3>{{firstName}} {{lastName}}</h3>'
}