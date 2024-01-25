export default {
    data() {
        return {
            tests: [],
            loaded: false,
        }
    },
    mounted(){
        this.getTests();
    },
    methods:{
      getTests(){
          let tests = [];
          $.ajax({
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
          this.tests = tests;
          this.loaded = true;
      }
    },
    template:
        '<h1>Тести</h1>' +
        '<div class="dashboard-content-child">' +
        '   <div v-if="loaded">' +
        '       <div v-if="tests.length!=0">' +
        '           <table class="table table-sm">' +
        '           <caption>Всього тестів - {{tests.length}}</caption>' +
        '           <tr v-for="test in tests">' +
        '               <td class="col-10"><h4>{{test.name}}</h4></td>' +
        '               <td class="col-2 justify-content-center">' +
        '                   <a class="btn btn-light"><i class="bi bi-box-arrow-in-right"></i> Відкрити</a>' +
        '               </td>' +
        '           </tr>' +
        '           </table>' +
        '       </div>' +
        '       <div v-else>' +
        '           <h2>Доступних тестів не знайдено</h2>' +
        '       </div>' +
        '   </div>' +
        '   <div v-else>' +
        '       <div class="d-flex align-items-center">' +
        '           <strong role="status">Loading...</strong>' +
        '           <div class="spinner-border ms-auto" aria-hidden="true"></div>' +
        '       </div>' +
        '   </div>' +
        '</div>'
}