export default {
    data(){
      return{
          testName: "",
          testDescription: "",
          modules:[]
      }
    },
    watch:{
        modules: {
            handler(newArray, oldArray){
                let str =
                    '<p>У вас є незбережені зміни</p>'
                $('#user-header').html(str);

            },
            deep: true
        }
    },
    methods:{
        addTestModule(){
            let module = {
                name: '',
                questions: []
            }
            this.modules.push(module);
        },
        addQuestion(index){
            let question = {
                description: '',
                type: 'RADIO',
                vars:[]
            }
            this.modules[index].questions.push(question);
        },
        addAnswerVariant(mIndex, qIndex){
            let answerVar = {
                description: '',
                isRight: false
            }
            this.modules[mIndex].questions[qIndex].vars.push(answerVar);
        },
        deleteModule(index){
            if(confirm("Ви точно хочете видалити модуль та всі питання в ньому?")){
                this.modules.splice(index, 1);
            }
        },
        deleteQuestion(mIndex, qIndex){
            if(confirm("Ви точно хочете видалити запитання:\n'"+this.modules[mIndex].questions[qIndex].description+"'?")){
                this.modules[mIndex].questions.splice(qIndex, 1);
            }
        },
        deleteAnswerVariant(mIndex, qIndex, aIndex){
            this.modules[mIndex].questions[qIndex].vars.splice(aIndex, 1);
        },
        saveOrUpdateTest(){
            let header = $('#user-header');
            header.html(
                '<div class="spinner-grow" role="status">\n' +
                '  <span class="visually-hidden">Loading...</span>\n' +
                '</div>');
            let data = {
                command: "saveOrUpdateTest",
                name: this.testName,
                description: this.testDescription,
                modules: this.modules
            }
            $.ajax({
                url: 'do',
                method: 'POST',
                data: JSON.stringify(data),
                success: function (response){
                    console.log(response);
                }
            })
            header.html('<p>Збережено</p>');
        }
    },
    template:
        '<div>' +
        '   <div class="dashboard-content my-1">' +
        '       <label class="form-label" for="testNameInput">Назва тесту</label>' +
        '       <input class="form-control" type="text" v-model="testName" name="testName" id="testNameInput" placeholder="Назва тесту">' +
        '       <label class="form-label" for="testDescriptionInput">Опис тесту</label>' +
        '       <textarea class="form-control" v-model="testDescription" id="testDescriptionInput" placeholder="Опис тесту"></textarea>' +
        '   </div>' +
        '   <div class="dashboard-content my-1" v-for="(module, index) in modules" :key="index">' +
        '       <div class="my-1" style="display: inline-flex; width: 100%">' +
        '           <h5>{{module.name}}</h5>' +
        '           <div style="display: flex; justify-content: space-between; margin-left: auto; align-items: center">' +
        '               <button class="btn btn-outline-danger" @click="deleteModule(index)"><i class="bi bi-trash3"></i> Видалити модуль</button>' +
        '           </div>' +
        '       </div>' +
        '       <input class="form-control" v-model="modules[index].name" placeholder="Назва модуля">' +
        '       <div class="dashboard-content-child my-1" v-for="(question, qIndex) in module.questions" :key="qIndex">' +
        '           <div class="my-1" style="display: inline-flex; width: 100%">' +
        '               <h5>Запитання</h5>' +
        '               <div style="display: flex; justify-content: space-between; margin-left: auto; align-items: center">' +
        '                       <button class="btn btn-outline-danger" @click="deleteQuestion(index, qIndex)"><i class="bi bi-trash3"></i> Видалити запитання</button>' +
        '               </div>' +
        '           </div>' +
        '           <textarea class="form-control" v-model="question.description" placeholder="Текст запитання"></textarea>' +
        '           <p class="form-label">Тип запитання</p>' +
        '           <select class="form-select my-1" v-model="question.type">' +
        '               <option value="RADIO">Радіокнопки</option>' +
        '               <option value="CHECK">Прапорці</option>' +
        '               <option value="TEXT">Короткий текст</option>' +
        '           </select>' +
        '           <div class="d-flex justify-content-center">' +
        '           <div>' +
        '               <div style="display: flex">' +
        '                   <p class="mx-1" style="width: 34px"></p>' +
        '                   <p class="mx-1" style="width: 250px"><i>Варіант відповіді</i></p>' +
        '                   <p class="mx-1"><i>Ключ</i></p>' +
        '               </div>' +
        '               <div class="mb-1" style="display: flex" v-for="(varAns, vIndex) in question.vars" :key="vIndex">' +
        '                   <button @click="deleteAnswerVariant(index, qIndex, vIndex)" class="btn btn-outline-danger"><i class="bi bi-trash3"></i></button>' +
        '                   <input class="form-control mx-1" style="width: 250px!important" v-model="varAns.description">' +
        '                   <input type="checkbox" class="form-check-input" v-model="varAns.isRight">' +
        '               </div>' +
        '               <button @click="addAnswerVariant(index, qIndex)" class="btn btn-light my-2">Додати варіант відповіді</button>' +
        '           </div>' +
        '           </div>' +
        '       </div>' +
        '       <button @click="addQuestion(index)" class="btn btn-light my-2">Додати запитання</button>' +
        '   </div>' +
        '   <div class="d-flex justify-content-center my-2">' +
        '       <div class="btn-group">' +
        '           <button class="btn btn-light" @click="addTestModule()">Додати модуль</button>' +
        '           <button class="btn btn-light" @click="saveOrUpdateTest()">Зберегти тест</button>' +
        '       </div>' +
        '   </div>' +
        '</div>'
}