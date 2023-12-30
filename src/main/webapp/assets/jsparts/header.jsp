<header class="p-1 px-2">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/assets/img/ds_logo_dark.png" alt="logo">
        <div class="m-0">
            <a><i><b>TESTING SYSTEM</b></i></a>
        </div>
    </div>
    <div class="sign-in" id="user-header">
        <p>${currentUser.login}</p>
    </div>
</header>
<hr class="m-0">
<div id="error-block" class="alert alert-danger" style="display: none">
    <h5 id="error-header"></h5>
    <hr>
    <p id="error-body"></p>
</div>
