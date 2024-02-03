export default {
    data(){
      return{
          tests:[],
          loaded: false
      }
    },
    mounted(){
        this.getAvailableTests().then(r => this.tests = r);
    },
    methods:{
      async getAvailableTests(){
          let tests = [];
          await $.ajax({
              url:"do",
              method: 'get',
              data: {
                  command: 'getAvailableTestList'
              },
              success: function (response) {
                  console.log(response);
                  for(let test in response){
                      let data = {
                          id: test.id,
                          name: test.name
                      };
                      tests.push(data);
                  }
              }
          })
          this.loaded = true;
          return tests;
      }
    },
    template:
        '   <div v-if="loaded">' +
        '       <h4>Доступні тести</h4>' +
        '       <div v-if="tests.length!=0">' +
        '           <table class="table table-sm">' +
        '           <caption>Всього тестів - {{tests.length}}</caption>' +
        '           <tr v-for="test in tests">' +
        '               <td class="col-11"><h4>{{test.name}}</h4></td>' +
        '               <td class="col-1">' +
        '                   <a :href="`do?command=loadTest&testId=`+test.id" class="btn btn-light"><i class="bi bi-box-arrow-in-right"></i> Відкрити</a>' +
        '               </td>' +
        '           </tr>' +
        '           </table>' +
        '       </div>' +
        '       <div v-else>' +
        '           <p>Доступних тестів не знайдено</p>' +
        '       </div>' +
        '   </div>' +
        '   <div v-else>' +
        '       <div class="d-flex align-items-center">' +
        '           <div class="spinner-border ms-auto" aria-hidden="true"></div>' +
        '           <strong role="status">Завантаження...</strong>' +
        '       </div>' +
        '   </div>'
}