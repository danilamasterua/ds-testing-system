export default {
    data(){
        return{
            tests:[],
            loaded: false
        }
    },
    mounted(){
        this.getOwnedTests();
    },
    methods:{
        async getOwnedTests(){
            let tests = [];
            await $.ajax({
                url:"do",
                method: 'get',
                data: {
                    command: 'getOwnedTestList'
                },
                dataType: 'json',
                success: function (response) {
                    if(!response.error) {
                        $.each(response, function (index, el) {
                            let tEl = {
                                id: el.id,
                                name: el.name
                            };
                            tests.push(tEl);
                        })
                    } else {
                        $("#error-header").text(response.errorCode)
                        $("#error-body").text(response.stackTrace);
                        $("#error-block").show();
                    }
                }
            })
            this.loaded = true;
            this.tests = tests;
        }
    },
    template:
        '   <div v-if="loaded">' +
        '       <div style="display: inline-flex; width: 100%;">' +
        '           <h4>Ваші тести</h4>' +
        '           <div style="display: flex; justify-content: space-between; margin-left: auto; align-items: center">' +
        '               <a :href="`do?command=creationTestPage`" class="btn btn-primary"><i class="bi bi-plus-circle"></i> Створити тест</a>' +
        '           </div>' +
        '       </div>' +
        '       <div v-if="tests.length!=0">' +
        '           <table class="table table-sm">' +
        '           <caption>Всього тестів - {{tests.length}}</caption>' +
        '           <tr v-for="test in tests">' +
        '               <td class="col-11"><i>{{test.name}}</i></td>' +
        '               <td class="col-1">' +
        '                   <a :href="`do?command=loadTest&testId=`+test.id" class="btn btn-light"><i class="bi bi-box-arrow-in-right"></i> Відкрити</a>' +
        '               </td>' +
        '           </tr>' +
        '           </table>' +
        '       </div>' +
        '       <div v-else>' +
        '           <p>Ваших тестів не знайдено</p>' +
        '       </div>' +
        '   </div>' +
        '   <div v-else>' +
        '       <div class="d-flex align-items-center">' +
        '           <div class="spinner-border ms-auto" aria-hidden="true"></div>' +
        '           <strong role="status">Завантаження...</strong>' +
        '       </div>' +
        '   </div>'
}