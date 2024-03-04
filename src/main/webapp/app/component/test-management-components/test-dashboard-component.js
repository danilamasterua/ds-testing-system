export default {
    data() {
        return{
            name: '',
            description: '',
            testId: 0,
            loaded: false
        }
    },
    mounted(){
        this.getTestInfo().then(r => {
            this.name = r.name;
            this.description = r.description;
            this.testId = r.testId;
            this.loaded = r.loaded;
        });
    },
    methods:{
        async getTestInfo(){
            let data = {
                name: '',
                description: '',
                testId: 0,
                loaded: false
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
                        data.loaded = true;
                    } else {
                        $("#error-header").text(response.errorCode)
                        $("#error-body").text(response.stackTrace);
                        $("#error-block").show();
                    }
                }
            })
            return data;
        }
    },
    template:
        '<div>' +
        '   <div v-if="loaded" class="dashboard-content my-1">' +
        '       <h1 id="testName">{{name}}</h1>' +
        '       <p id="testDescription">{{description}}</p>' +
        '   </div>' +
        '   <div v-else>' +
        '       <p>Loading...</p>' +
        '   </div>' +
        '</div>'
}