export default {
    data(){
      return{
          testName: "",
          testDescription: "",
          modules:[]
      }
    },
    template:
        '<div>' +
        '   <div>' +
        '       <label for="testNameInput">Назва тесту</label>' +
        '       <input class="form-control" type="text" v-model="testName" name="testName" id="testNameInput" placeholder="Назва тесту">' +
        '       <label for="testDescriptionInput">Опис тесту</label>' +
        '       <textarea class="form-control" v-model="testDescription" id="testDescriptionInput" placeholder="Опис тесту"></textarea>' +
        '   </div>' +
        '</div>'
}