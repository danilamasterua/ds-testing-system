import {createApp} from 'vue';
import creationTestComponent from "./creation-test-component.js";
import passingTestComponent from "./passing-test-component.js";
export default {
    data() {
        return{
            name: '',
            description: '',
            testId: 0,
            testOwnerId: 0,
            userId: 0,
            userRole: '',
            isUpdatable: false,
            loaded: false,
            app: undefined
        }
    },
    async mounted(){
        await this.getTestInfo().then(r => {
            this.name = r.name;
            this.description = r.description;
            this.testId = r.testId;
            this.testOwnerId = r.testOwnerId;
            this.loaded = r.loaded;
        });
        await this.getCurrentUserInfo().then(r=>{
            this.userId = r.userId;
            this.userRole = r.role;
        })
        if(this.userId===this.testOwnerId){
            this.isUpdatable=true
        }
        console.log(this.userId===this.testOwnerId);
        this.loaded = true;
    },
    methods:{
        async getTestInfo(){
            let data = {
                name: '',
                description: '',
                testId: 0,
                testOwnerId: 0,
            }
            await $.ajax({
                url: 'do',
                method: 'get',
                data: {
                    command: 'getCurrentTestMeta'
                },
                success: function (response){
                    console.log(response)
                    if(!response.error) {
                        data.name = response.name;
                        data.description = response.description;
                        data.testId = Number.parseInt(response.id);
                        data.testOwnerId = Number.parseInt(response.owner_id);
                    } else {
                        $("#error-header").text(response.errorCode)
                        $("#error-body").text(response.stackTrace);
                        $("#error-block").show();
                    }
                }
            })
            return data;
        },
        async getCurrentUserInfo(){
            let data = {
                role:'',
                userId:0
            }
            await $.ajax({
                url: 'do',
                method: 'get',
                data:{
                    command: 'getCurrentUser'
                },
                success: function (response){
                    console.log(response);
                    if(!response.error){
                        data.role = response.userAccessLevel;
                        data.userId = Number.parseInt(response.id);
                    } else {
                        $("#error-header").text(response.errorCode)
                        $("#error-body").text(response.stackTrace);
                        $("#error-block").show();
                    }
                }
            })
            return data;
        },
        openTestUpdateForm(){
            this.app=createApp(creationTestComponent);
            this.appMount();
        },
        openTestPassingApp(){
            this.app=createApp(passingTestComponent);
            this.appMount()
        },
        appMount(){
            this.app.mount("#test-management-block");
        }
    },
    template:
        '<div>' +
        '   <div v-if="loaded" class="dashboard-content my-1">' +
        '       <div style="display: inline-flex; width: 100%;">' +
        '           <h1 id="testName">{{name}}</h1>' +
        '           <div style="display: flex; justify-content: space-between; margin-left: auto; align-items: center">' +
        '                   <div class="btn-group">' +
        '                       <button class="btn btn-light" @click="openTestPassingApp()">Розпочати тест</button>' +
        '                       <template v-if="userId==testOwnerId">' +
        '                           <button class="btn btn-light" @click="openTestUpdateForm()">Редагувати тест</button>' +
        '                           <button class="btn btn-danger">Видалити тест</button>' +
        '                       </template>' +
        '                   </div>' +
        '               </div>' +
        '       </div>' +
        '       <p id="testDescription">{{description}}</p>' +
        '   </div>' +
        '   <div v-else>' +
        '       <p>Loading...</p>' +
        '   </div>' +
        '</div>'
}