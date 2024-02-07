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
                console.log(newArray);
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
                vars:[]
            }
            this.modules[index].questions.push(question);
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
        '       <p class="form-label">{{modules[index].name}}</p>' +
        '       <input class="form-control" v-model="modules[index].name" placeholder="Назва модуля">' +
        '       <div class="dashboard-content-child mb-2" v-for="(question, qIndex) in module.questions" :key="qIndex">' +
        '           <p class="form-label">Запитання</p>' +
        '           <textarea class="form-control" v-model="modules[index].questions[qIndex].description"></textarea>' +
        '       </div>' +
        '       <button @click="addQuestion(index)" class="btn btn-light my-2">Додати запитання</button>' +
        '   </div>' +
        '   <div class="d-flex justify-content-center my-2">' +
        '       <button class="btn btn-light" @click="addTestModule()">Додати модуль</button>' +
        '   </div>' +
        '</div>'
}