import CreationTestComponent from "./vue/component/test-management-components/creation-test-component";
import TestDashboardComponent from "./vue/component/test-management-components/test-dashboard-component";

export default {
    async getTestModules(){
        let modules = [];
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
                    modules.push(mod);
                })
            }
        })
        return modules;
    },
    async openTestUpdateFormMeta() {
        let data = {
            isCurrentTestExist: false,
            testName: '',
            testDescription: ''
        }
        await $.ajax({
            url: 'do',
            method: 'get',
            data: {
                command: "openTestUpdateForm-Meta"
            },
            success: function (response){
                console.log(response);
                if(response!=="false"){
                    console.log("Loading test data to data object")
                    if(!response.error){
                        data.isCurrentTestExist = true;
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
        return data;
    },
    saveOrUpdateTest(data) {
        $.ajax({
            url: 'do',
            method: 'POST',
            data: JSON.stringify(data),
            success: function (response){
                console.log(response);
            }
        })
    },
    async getTestPageStatus(){
        let status = false;
        await $.ajax({
            url: 'do',
            data:{
                command: 'getTestPageStatus'
            },
            success: function (response) {
                status = response.testPageStatus;
            }
        })
        return status;
    }
}