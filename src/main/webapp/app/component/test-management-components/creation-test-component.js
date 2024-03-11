import {ref} from 'vue'
export default {
    data(){
      return{
          loaded: false,
          testName: ref(""),
          testDescription: ref(""),
          modules: ref([])
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
    async mounted(){
        let data = {
            loaded: false,
            testName:"",
            testDescription:"",
            modules:[]
        };
        let isCurrentTestExist = false;
        await $.ajax({
            url: 'do',
            method: 'get',
            data: {
                command: "openTestUpdateForm-Meta"
            },
            success: function (response){
                console.log(response);
                if(response!==false){
                    console.log("Loading test data to data object")
                    if(!response.error){
                        isCurrentTestExist = true;
                        data.testName = response.name;
                        data.testDescription = response.description;
                    } else {
                        $("#error-header").text(response.errorCode)
                        $("#error-body").text(response.stackTrace);
                        $("#error-block").show();
                    }
                }
            }
        });
        console.log(isCurrentTestExist);
        if(isCurrentTestExist){
            await $.ajax({
                url:'do',
                method: 'get',
                data: {
                    command: "getTestModules"
                },
                success: function (response){
                    $.each(response, function (index, module){
                        let mod = {
                            name: '',
                            questions: []
                        }
                        mod.name = module.name;
                        $.each(module.questions, function (qIndex, question){
                            let quest = {
                                description: '',
                                type: '',
                                vars: []
                            }
                            quest.description = question.description;
                            quest.type = question.questionType;
                            $.each(question.answerVariants, function (aIndex, answerVar){
                                let ansVar = {
                                    description: '',
                                    isRight: false
                                }
                                ansVar.description = answerVar.description;
                                ansVar.isRight = answerVar.isRight;
                                quest.vars.push(ansVar);
                            })
                            mod.questions.push(quest);
                        })
                        data.modules.push(mod);
                    })
                }
            })
            console.log(data.testName+"\n"+data.testDescription);
            this.testName = data.testName;
            this.testDescription = data.testDescription;
            this.modules = data.modules;
        }
        this.loaded = true;
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
                '<div class="spinner-border" role="status">\n' +
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
        '<div v-if="loaded">' +
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
        '</div>' +
        '<div v-else>' +
        '   <div class="d-flex align-items-center">' +
        '       <strong role="status">Зачекайте...</strong>' +
        '       <div class="spinner-border ms-auto" aria-hidden="true"></div>' +
        '   </div>' +
        '</div>'
}