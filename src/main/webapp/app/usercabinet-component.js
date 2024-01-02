export default {
    mounted() {
        $.ajax({
            method: 'GET',
            url: "do",
            data: {
                command: "getCurrentUser"
            },
            success: function (response){
                let fullName = response.firstName+" "+response.lastName;
                $("#fullName").text(fullName);
                $("#email").text(response.email);
            }
        })
    },
    template:
        '<h1>Ваш профіль</h1>' +
        '<div class="dashboard-content-child">' +
        '   <div class="user-card">' +
        '       <h4 id="fullName"></h4>' +
        '       <p><b>Email:</b><a id="email"></a></p>' +
        '       <div class="btn-group">' +
        '           <button class="btn btn-primary">Змінити пароль</button>' +
        '           <button class="btn btn-danger">Вийти</button>' +
        '       </div>' +
        '   </div>' +
        '   <div id="usercabinet-do">' +
        '   </div>' +
        '</div>'
}