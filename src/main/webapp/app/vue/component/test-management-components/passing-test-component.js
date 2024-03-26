import {ref} from 'vue'
import ajaxqueries from "../../../ajaxqueries.js";
export default {
    data(){
        return{
            ended: ref(false),
            loaded: ref(false),
            loading: ref(true),
            moduleIndex: 0,
            questionIndex: 0,
            currentQuestion: ref({}),
            modules: ref([])
        }
    },
    async mounted(){
        await ajaxqueries.getTestModules().then(r=>{this.modules=r});
        await this.nextQuestion();
        this.loading = false;
        this.loaded = true;
    },
    methods: {
        nextQuestion(){
            if(this.moduleIndex<this.modules.length){
                if(this.modules[this.moduleIndex].questions.length===this.questionIndex){
                    this.moduleIndex++;
                    this.questionIndex=0;
                    this.nextQuestion();
                } else {
                    this.currentQuestion = this.modules[this.moduleIndex].questions[this.questionIndex];
                    this.questionIndex++;
                }
            } else {
                this.loaded = false;
                this.ended = true;
            }
        }
    },
    template:
        '<div class="d-flex justify-content-center">' +
        '   <div v-if="loaded" id="q-content" class="dashboard-content my-1" style="width: 500px!important;">' +
        '       <small><i>{{modules[moduleIndex].name}}</i></small>' +
        '       <hr>' +
        '       <p>{{currentQuestion.description}}</p>' +
        '       <div class="dashboard-content-child">' +
        '           <p v-for="(va, index) in currentQuestion.vars" :key="index">' +
        '               <input v-if="currentQuestion.type==\'RADIO\'" type="radio" class="form-check-input" name="ansVar" :value="va.description">' +
        '               <input v-if="currentQuestion.type==\'CHECK\'" type="checkbox" class="form-check-input" name="ansVar" :value="va.description">' +
        '               <b class="form-check-label" v-if="currentQuestion.type!=\'TEXT\'">{{va.description}}</b>' +
        '               <input type="text" class="form-control" v-if="currentQuestion.type==\'TEXT\'">' +
        '           </p>' +
        '       <button class="btn btn-light my-2" @click="nextQuestion()">Наступне запитання</button>' +
        '       </div>' +
        '   </div>' +
        '   <div v-if="ended">' +
        '       <p>===LAST QUESTION===</p>' +
        '   </div>' +
        '   <div v-if="loading">Loading...</div>' +
        '</div>'
}